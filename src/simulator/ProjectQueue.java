/**
 * Junyoung Kim 109309965 junyoung.kim@stonybrook.edu R06
 */
public class ProjectQueue {
    public final int CAPACITY = 100;
    private Project[] data;
    private int front;
    private int rear;

    /**
     * A method that makes a queue of Project variables.
     */
    public ProjectQueue() {
        front = -1;
        rear = -1;
        data = new Project[CAPACITY];
    }

    /**
     * A method that put Project variables into a queue
     * @param c Project
     * @throws FullQueueException if the queue is full, throw the exception
     */
    public void enqueue(Project c) throws FullQueueException {
        if((rear+1)%CAPACITY == front)
            throw new FullQueueException();
        if(front == -1){
            front = 0; rear = 0;
        }
        else rear = (rear+1)%CAPACITY;
        data[rear] = c;
    }

    /**
     * A method that take out the project from the queue
     * @return the project that took out
     * @throws EmptyQueueException if the queue is empty, throw the exception.
     */
    public Project dequeue() throws EmptyQueueException {
        Project answer;
        if(front == -1)
            throw new EmptyQueueException();
        answer = data[front];
        if(front == rear){
            front = -1; rear = -1;
        }
        else front = (front+1)%CAPACITY;
        return answer;
    }

    /**
     * A method that gets the project which is located at the front of the queue.
     * @return A project which located at the front of the queue
     */
    public Project peek(){
        return data[front];
    }

    /**
     * A method that finds out the size of the queue
     * @return the size of the queue
     */
    public int size(){
        int count = 0;
        for(int i = 0; i < CAPACITY ; i ++)
        {
            if(data[i] != null)
            {
                count++;
            }
        }
        return count;
    }

    /**
     * A method that find out the queue is empty
     * @return true if the queue is empty
     */
    public boolean isEmpty(){
        return (front == -1);
    }

    /**
     * A method that find out the elements of the queue are equal to the object
     * @param obj the object that is going to compare with the elements of the queue
     * @return true is the object is equal to elements.
     */
    public boolean equals(Object obj){
        return(data[front] == obj);
    }
}
