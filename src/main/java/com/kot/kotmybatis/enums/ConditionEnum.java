package com.kot.kotmybatis.enums;


public enum ConditionEnum {

    /**
     * 筛选条件
     */
    EQ("="),
    NEQ("<>"),
    GT(">"),
    LT("<"),
    GTE(">="),
    LTE("<="),
    IN("in"),
    NIN("not in");

    public String oper;

    ConditionEnum(String oper) {
        this.oper = oper;
    }

    public static boolean contains(ConditionEnum conditionEnum) {
        for (ConditionEnum anEnum : ConditionEnum.values()) {
            if (anEnum == conditionEnum) {
                return true;
            }
        }
        return false;
    }
}
