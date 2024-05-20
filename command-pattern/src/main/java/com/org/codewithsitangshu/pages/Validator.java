package com.org.codewithsitangshu.pages;

/**
 * Validator interface defines a contract for classes that perform validation.
 * Implementing classes should provide a concrete implementation of the validate method.
 */
public interface Validator {
    /**
     * Validates a specific condition.
     *
     * @return true if validation succeeds, false otherwise
     */
    boolean validate();
}
