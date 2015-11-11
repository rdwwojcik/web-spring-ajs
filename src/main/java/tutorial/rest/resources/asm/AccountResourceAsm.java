package tutorial.rest.resources.asm;

import tutorial.core.model.Account;
import tutorial.rest.mvc.AccountController;
import tutorial.rest.resources.AccountResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by Radek on 2015-08-28.
 */
public class AccountResourceAsm extends ResourceAssemblerSupport<Account, AccountResource> {


    public AccountResourceAsm() {
        super(AccountController.class, AccountResource.class);
    }

    @Override
    public AccountResource toResource(Account account) {
        AccountResource accountResource = new AccountResource();
        accountResource.setName(account.getName());
        accountResource.setPassword(account.getPassword());

        Link link = linkTo(AccountController.class).slash(account.getId()).withSelfRel();
        accountResource.add(link);

        return accountResource;
    }
}
