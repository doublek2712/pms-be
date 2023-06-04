package com.douk.PMS.utils;

import org.springframework.beans.factory.annotation.Value;

public class TaxCalculator {

    private static Double[] taxRare = {0.05, 0.1, 0.15, 0.2, 0.25, 0.3, 0.35};

    private static Long[] taxLevel = {
            0L,
            5000000L,
            10000000L,
            18000000L,
            32000000L,
            52000000L,
            80000000L,
    };

    //@Value("${business.personalDeductions}")
    private static Long personalDeductions = 11000000L;

    public static Long calTax(Long income){
        Long res = 0L;
        Long taxableIncome = income - personalDeductions;
        for(int i = 0; i <= taxLevel.length - 1 - 1 && taxableIncome >= taxLevel[i]; i++){
            Long minxOfThisLevel = Long.min(taxLevel[i+1] , taxableIncome);
            res += (long) ( (minxOfThisLevel - taxLevel[i]) * taxRare[i]);
        }

        if(taxableIncome >= taxLevel[taxLevel.length - 1])
            res += (long) ( (taxableIncome - taxLevel[taxLevel.length - 1]) * taxRare[taxLevel.length - 1]);

        return res;

    }
}
