package interview.commvault.synchronization;

public class Livelock {
    static class Spoon {
    private Diner owner;

    public Spoon(Diner d) {
        owner = d;
    }

    public Diner getOwner() {
        return owner;
    }

    public synchronized void setOwner(Diner d) {
        owner = d;
    }

    public synchronized void use() {
        System.out.printf("%s has eaten!", owner.name);
    }
    }

    static class Diner {
    private String name;
    private boolean isHungry;

    public Diner(String n) {
        name = n;
        isHungry = true;
    }

    public String getName() {
        return name;
    }

    public boolean isHungry() {
        return isHungry;
    }

    public void eatWith(Spoon spoon, Diner spouse) {
        while (isHungry) {
        // Don't have the spoon, so wait patiently for spouse.
        if (spoon.owner != this) {
            try {
            Thread.sleep(1);
            } catch (InterruptedException e) {
            continue;
            }
            continue;
        }

        // If spouse is hungry, insist upon passing the spoon.
        if (spouse.isHungry()) {
            System.out.printf("%s: You eat first my darling %s!%n",
                name, spouse.getName());
            spoon.setOwner(spouse);
            continue;
        }

        // Spouse wasn't hungry, so finally eat
        spoon.use();
        isHungry = false;
        System.out.printf("%s: I am stuffed, my darling %s!%n", name,
            spouse.getName());
        spoon.setOwner(spouse);
        }
    }
    }

    public static void main(String[] args) {
    final Diner husband = new Diner("Bob");
    final Diner wife = new Diner("Alice");

    final Spoon s = new Spoon(husband);

    new Thread(new Runnable() {
        public void run() {
        husband.eatWith(s, wife);
        }
    }).start();

    new Thread(new Runnable() {
        public void run() {
        wife.eatWith(s, husband);
        }
    }).start();
    }
}

// //thread 1
// getLocks12(lock1, lock2)
// {
// lock1.lock();
// while (lock2.locked())
// {
// // attempt to step aside for the other thread
// lock1.unlock();
// wait();
// lock1.lock();
// }
// lock2.lock();
// }
//
// //thread 2
// getLocks21(lock1, lock2)
// {
// lock2.lock();
// while (lock1.locked())
// {
// // attempt to step aside for the other thread
// lock2.unlock();
// wait();
// lock2.lock();
// }
// lock1.lock();
// }