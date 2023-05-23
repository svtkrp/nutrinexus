package com.svtkrp.nutrinexus.form;

import com.svtkrp.nutrinexus.validation.Orders;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserEditCredsForm {

    @NotBlank(message = "Username is required", groups = Orders.Order1.class)
    @Size(min = 5, max = 30, message = "Username should be between 5 and 30 characters", groups = Orders.Order2.class)
    @Pattern(regexp = "^[a-z_][a-z0-9_.]{4,29}$",
            message = "Username can only contain 0-9, a-z, dot (.) and underscore (_) and start with a-z or underscore (_)",
            groups = Orders.Order3.class)
    private String username;

    @NotBlank(message = "Email is required", groups = Orders.Order1.class)
    @Size(min = 3, max = 100, message = "Email should be between 3 and 100 characters", groups = Orders.Order2.class)
    /*todo @Pattern(regexp = "")*/
    private String email;

    @NotBlank(message = "Password is required", groups = Orders.Order1.class)
    @Size(min = 8, max = 40, message = "Password should be between 8 and 40 characters", groups = Orders.Order2.class)
    /*todo @Pattern(regexp = "")*/
    private String password;

}
