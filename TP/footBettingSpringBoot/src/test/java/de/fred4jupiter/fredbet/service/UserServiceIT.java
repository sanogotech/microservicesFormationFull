package de.fred4jupiter.fredbet.service;

import de.fred4jupiter.fredbet.TransactionalIntegrationTest;
import de.fred4jupiter.fredbet.domain.AppUser;
import de.fred4jupiter.fredbet.domain.AppUserBuilder;
import de.fred4jupiter.fredbet.domain.Bet;
import de.fred4jupiter.fredbet.repository.BetRepository;
import de.fred4jupiter.fredbet.security.FredBetUserGroup;
import de.fred4jupiter.fredbet.service.user.UserAlreadyExistsException;
import de.fred4jupiter.fredbet.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.junit.jupiter.api.Assertions.*;

@TransactionalIntegrationTest
public class UserServiceIT {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private BeanFactory beanFactory;

    @Test
    public void avoidDuplicateUser() {
        AppUser appUser = AppUserBuilder.create().withUsernameAndPassword("mustermann", "mustermann").withUserGroup(FredBetUserGroup.ROLE_USER)
                .build();

        userService.createUser(appUser);

        try {
            userService.createUser(appUser);
            fail("UserAlreadyExistsException should be thrown");
        } catch (UserAlreadyExistsException e) {
            // expected
        }
    }

    @Test
    public void changePassword() {
        final String oldPassword = "blabla";
        final String newPassword = "mega";

        final AppUser appUser = AppUserBuilder.create().withDemoData().withPassword(oldPassword).build();
        userService.createUser(appUser);

        assertNotNull(appUser.getId());

        userService.changePassword(appUser.getId(), oldPassword, newPassword);

        AppUser found = userService.findByUserId(appUser.getId());
        assertNotNull(found);
        assertTrue(passwordEncoder.matches(newPassword, found.getPassword()));
    }

    @Test
    public void changePasswordOldPasswordIsNotCorrect() {
        final String plainPassword = "hans";
        final String plainNewPassword = "mueller";
        final AppUser appUser = AppUserBuilder.create().withDemoData().withPassword(plainPassword).build();
        userService.createUser(appUser);

        assertNotNull(appUser.getId());

        try {
            userService.changePassword(appUser.getId(), "wrongOldPassword", plainNewPassword);
            fail("OldPasswordWrongException should be thrown");
        } catch (OldPasswordWrongException e) {
            // OK
        }
    }

    @Test
    public void updatePrivilegesAndNotPassword() {
        final AppUser appUser = AppUserBuilder.create().withDemoData().build();
        userService.createUser(appUser);
        assertEquals(1, appUser.getRoles().size());

        // add role
        appUser.addUserGroup(FredBetUserGroup.ROLE_USER_ENTER_RESULTS);
        assertEquals(2, appUser.getRoles().size());
        userService.updateUser(appUser.getId(), appUser.getRoles(), false);

        AppUser foundAppUser = userService.findByUserId(appUser.getId());
        assertNotNull(foundAppUser);

        assertEquals(appUser.getUsername(), foundAppUser.getUsername());
        assertEquals(appUser.getPassword(), foundAppUser.getPassword());
    }

    @Test
    public void renameUser() {
        FredBetUsageBuilder fredBetUsageBuilder = beanFactory.getBean(FredBetUsageBuilder.class);

        AppUser appUser = fredBetUsageBuilder.withAppUser().withMatch().withBet().build();

        final String oldUserName = new String(appUser.getUsername());

        final String newUsername = "Klarky";

        userService.renameUser(oldUserName, newUsername);

        AppUser foundUser = userService.findByUserId(appUser.getId());
        assertNotNull(foundUser);
        assertEquals(newUsername, foundUser.getUsername());

        List<Bet> betsByOldName = this.betRepository.findByUserName(oldUserName);
        assertThat(betsByOldName.size()).isEqualTo(0);

        List<Bet> betsByNewName = this.betRepository.findByUserName(newUsername);
        assertNotNull(betsByNewName);
        assertThat(betsByNewName.size()).isGreaterThan(0);
    }
}
