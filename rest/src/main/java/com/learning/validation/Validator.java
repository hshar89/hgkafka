package com.learning.validation;

import com.learning.topic.exception.TopicValidationException;

public interface Validator<T> {
  void validate(T object) throws TopicValidationException;
}
