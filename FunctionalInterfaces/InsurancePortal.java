interface SecurityUtils {
    // Static method to validate password strength
    static boolean isStrongPassword(String password) {
        if (password == null) return false;

        // Rules: min 8 chars, at least 1 uppercase, 1 lowercase, 1 digit, 1 special char
        boolean lengthRule = password.length() >= 8;
        boolean upperCaseRule = password.chars().anyMatch(Character::isUpperCase);
        boolean lowerCaseRule = password.chars().anyMatch(Character::isLowerCase);
        boolean digitRule = password.chars().anyMatch(Character::isDigit);
        boolean specialCharRule = password.chars().anyMatch(ch -> "!@#$%^&*()_+-=[]{}|;:,.<>?".indexOf(ch) >= 0);

        return lengthRule && upperCaseRule && lowerCaseRule && digitRule && specialCharRule;
    }
}

public class InsurancePortal {
    public static void main(String[] args) {
        String[] testPasswords = {
            "pass123",          // weak (no uppercase, special char, too short)
            "Password123",      // weak (no special char)
            "Pass@123",         //  strong
            "InsureMe#2025"     //  strong
        };

        for (String pwd : testPasswords) {
            if (SecurityUtils.isStrongPassword(pwd)) {
                System.out.println(" Strong password: " + pwd);
            } else {
                System.out.println(" Weak password: " + pwd);
            }
        }
    }
}
