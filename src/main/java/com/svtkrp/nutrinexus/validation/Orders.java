package com.svtkrp.nutrinexus.validation;

import jakarta.validation.GroupSequence;

public abstract class Orders {
    public interface Order1 {
    }

    public interface Order2 {
    }

    public interface Order3 {
    }

    @GroupSequence({Order1.class, Order2.class, Order3.class})
    public interface All {
    }

    public interface None {
    }
}
