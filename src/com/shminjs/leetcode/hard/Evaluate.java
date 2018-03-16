package com.shminjs.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shimin on 2017/11/26.
 */
public class Evaluate {
    public int evaluate(String expression) {
        Map<String, Integer> map = new HashMap<>();
        return evaluate(expression, map);
    }

    private int evaluate(String expression, Map<String, Integer> map) {
        if (expression.startsWith("(let")) {
            // let语句
            Map<String, Integer> envir = new HashMap<>(map);
            return let(expression, envir);
        } else if (expression.startsWith("(add")) {
            // add语句
            Map<String, Integer> envir = new HashMap<>(map);
            return add(expression, envir);
        } else if (expression.startsWith("(mul")) {
            // mul语句
            Map<String, Integer> envir = new HashMap<>(map);
            return mul(expression, envir);
        } else if (map.containsKey(expression)) {
            return map.get(expression);
        } else {
            return Integer.parseInt(expression);
        }
    }

    private int let(String expression, Map<String, Integer> envir) {
        String v = "";
        int ind = "(let".length();  // 从第一个空格开始，后面需要根据情况分割v,e
        while (ind < expression.length()) {
            if (expression.charAt(ind) == ' ') {
                // 空格开始，求解该式
                ind++;
                int count = 0;
                int start = ind;
                while (ind < expression.length() - 1) {
                    // 保证能把括号囊进入(let a1 3 b2 (add a1 1) b2)
                    if (expression.charAt(ind) == '(') {
                        count++;
                    } else if (expression.charAt(ind) == ')') {
                        count--;
                        if (count == 0) {
                            // 此时应该肯定可以退出了
                            ind++;
                            break;
                        }
                    } else if (expression.charAt(ind) == ' ' && count == 0) {
                        break;
                    }
                    ind++;
                }
                String e = expression.substring(start, ind);
                if (v.equals("")) {
                    v = e;
                } else {
                    envir.put(v, evaluate(e, envir));
                    v = "";
                }
            } else {
                ind++;
            }
        }
        return evaluate(v, envir);
    }

    private int add(String expression, Map<String, Integer> envir) {
        // (add 1 2)
        String e1 = "", e2 = "";
        int ind = "(add".length();
        while (ind < expression.length()) {
            if (expression.charAt(ind) == ' ') {
                ind++;
                int count = 0;
                int start = ind;
                while (ind < expression.length() - 1) {
                    // 保证能把括号囊进入(let a1 3 b2 (add a1 1) b2)
                    if (expression.charAt(ind) == '(') {
                        count++;
                    } else if (expression.charAt(ind) == ')') {
                        count--;
                        if (count == 0) {
                            // 此时应该肯定可以退出了
                            ind++;
                            break;
                        }
                    } else if (expression.charAt(ind) == ' ' && count == 0) {
                        break;
                    }
                    ind++;
                }
                if (e1.equals("")) {
                    e1 = expression.substring(start, ind);
                } else {
                    e2 = expression.substring(start, ind);
                    break;
                }
            } else {
                ind++;
            }
        }
        return evaluate(e1, envir) + evaluate(e2, envir);
    }

    private int mul(String expression, Map<String, Integer> envir) {
        // (mul 1 2)
        String e1 = "", e2 = "";
        int ind = "(add".length();
        while (ind < expression.length()) {
            if (expression.charAt(ind) == ' ') {
                ind++;
                int count = 0;
                int start = ind;
                while (ind < expression.length() - 1) {
                    // 保证能把括号囊进入(let a1 3 b2 (add a1 1) b2)
                    if (expression.charAt(ind) == '(') {
                        count++;
                    } else if (expression.charAt(ind) == ')') {
                        count--;
                        if (count == 0) {
                            // 此时应该肯定可以退出了
                            ind++;
                            break;
                        }
                    } else if (expression.charAt(ind) == ' ' && count == 0) {
                        break;
                    }
                    ind++;
                }
                if (e1.equals("")) {
                    e1 = expression.substring(start, ind);
                } else {
                    e2 = expression.substring(start, ind);
                    break;
                }
            } else {
                ind++;
            }
        }
        return evaluate(e1, envir) * evaluate(e2, envir);
    }

    public static void main(String[] args) {
        System.out.println(new Evaluate().evaluate("(let x 2 (mult x (let x 3 y 4 (add x y))))"));
    }
}
