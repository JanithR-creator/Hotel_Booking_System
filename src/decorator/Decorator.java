package decorator;

interface Spend {
    double getAmount();
}

class Reservation implements Spend {
    private double amount;

    public Reservation(double amount) {
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount;
    }
}

abstract class SpendDecorator implements Spend {
    public abstract double getAmount();
}

class Restaurant extends SpendDecorator {
    private Spend spend;
    private double amount;
    public double serviceCharge = amount * 0.3;

    public Restaurant(Spend spend, double amount) {
        this.spend = spend;
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return spend.getAmount() + amount + serviceCharge;
    }
}

class Gym extends SpendDecorator {
    private Spend spend;
    private double amount;

    public Gym(Spend spend, double amount) {
        this.spend = spend;
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount + spend.getAmount();
    }
}

class Bar extends SpendDecorator {
    private Spend spend;
    private double amount;

    public Bar(Spend spend, double amount) {
        this.spend = spend;
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount + spend.getAmount();
    }
}

class Spa extends SpendDecorator {
    private Spend spend;
    private double amount;

    public Spa(Spend spend, double amount) {
        this.spend = spend;
        this.amount = amount;
    }

    @Override
    public double getAmount() {
        return amount + spend.getAmount();
    }
}
