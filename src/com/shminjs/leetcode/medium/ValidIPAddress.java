package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/8/24.
 * 468
 */
public class ValidIPAddress {
    public String validIPAddress(String IP) {
        if (IP.contains(".")) {
            // check for IPv4
            String[] ips = IP.split("\\.");
            if (ips.length != 4) return "Neighter";
            for (int i = 0; i < 4; i++) {
                if (!isValidIPv4(ips[i])) return "Neither";
            }
            return "IPv4";
        } else if (IP.contains(":")) {
            // check for IPv6
            if (IP.contains("::")) {
                return "Neither";
            } else {
                String[] ips = IP.split(":");
                if (ips.length != 8) return "Neither";
                for (int i = 0; i < 8; i++) {
                    if (!isValidIPv6(ips[i])) return "Neither";
                }
                return "IPv6";
            }
        } else {
            return "Neigher";
        }
    }

    private boolean isValidIPv4(String ip) {
        if (ip.length() > 3 || ip.length() == 0 || (ip.charAt(0) == '0' && ip.length() > 1))
            return false;
        for (int i = 0; i < ip.length(); i++) {
            if (!Character.isDigit(ip.charAt(i))) return false;
        }
        if (Integer.parseInt(ip) > 255) return false;
        return true;
    }

    private boolean isValidIPv6(String ip) {
        boolean flag = true;
        if (ip.length() > 4 || ip.length() == 0)
            flag = false;
        for (int i = 0; i < ip.length(); i++) {
            if (Character.isLetterOrDigit(ip.charAt(i))) {
                if ((Character.isLowerCase(ip.charAt(i)) && ip.charAt(i) > 'f') || (Character.isUpperCase(ip.charAt(i)) && ip.charAt(i) > 'F')) {
                    flag = false;
                    break;
                }
            } else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        System.out.println(new ValidIPAddress().validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
    }
}
