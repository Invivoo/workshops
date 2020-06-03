package com.example.demo.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ThursDayCondition  implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return DayOfWeek.THURSDAY.equals(LocalDate.now().getDayOfWeek());
    }
}