/**
 * FileName: UserNotFoundException
 * Author: jane
 * Date: 2023/1/8 12:45
 * Description:
 * Version:
 */

package com.jane.buyherebackend.error;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }
}
