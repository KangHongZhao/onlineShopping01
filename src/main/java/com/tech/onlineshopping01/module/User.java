package com.tech.onlineshopping01.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class User {
    String userName;
    String userEmail;

}
