package policies;

import animal.*;
import queue.Queue;

import javax.naming.SizeLimitExceededException;

public abstract class Policies {

  public static Queue<Animal> produce(Class typeClass, int size) throws SizeLimitExceededException {

    Queue<Animal> upperBoundQueue = new Queue(size);
    if (typeClass.isAssignableFrom(Chordal.class)) {
      upperBoundQueue.add(new Chordal());
    }
    if (typeClass.isAssignableFrom(Mammal.class)) {
      upperBoundQueue.add(new Mammal());
    }
    if (typeClass.isAssignableFrom(Predatory.class)) {
      upperBoundQueue.add(new Predatory());
    }
    if (typeClass.isAssignableFrom(Canine.class)) {
      upperBoundQueue.add(new Canine());
    }
    if (typeClass.isAssignableFrom(Dog.class)) {
      upperBoundQueue.add(new Dog());
    }
    if (typeClass.isAssignableFrom(Wolfish.class)) {
      upperBoundQueue.add(new Wolfish());
    }
    if (typeClass.isAssignableFrom(GermanShepherdDog.class)) {
      upperBoundQueue.add(new GermanShepherdDog());
    }
    if (typeClass.isAssignableFrom(EurasianWolf.class)) {
      upperBoundQueue.add(new EurasianWolf());
    }
    if (typeClass.isAssignableFrom(GrayWolf.class)) {
      upperBoundQueue.add(new GrayWolf());
    }
    return upperBoundQueue;
  }

  public static Queue<Animal> consume(Queue queue, Class typeClass, int size) throws SizeLimitExceededException {
    if (size < 1) {
      throw new IllegalArgumentException("Parents are not in the queue");
    }
    Queue<Animal> lowerBoundQueue = new Queue(size);
    for (int i = 0; i < queue.getSize(); i++) {
      {
        if (queue.get(i).getClass().isAssignableFrom(typeClass)) {
          lowerBoundQueue.add(queue.get(i));
        }
      }
    }
    return lowerBoundQueue;
  }
}
