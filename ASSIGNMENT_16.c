#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

#define BUFFER_SIZE 5
#define ITEMS_TO_PRODUCE 10

int buffer[BUFFER_SIZE];
int in = 0;
int out = 0;

sem_t empty;
sem_t full;
pthread_mutex_t mutex;

/* Producer Thread */
void* producer(void* arg) {
    int item;

    for(int i = 1; i <= ITEMS_TO_PRODUCE; i++) {

        item = i;

        // Wait if buffer is full
        sem_wait(&empty);

        // Lock critical section
        pthread_mutex_lock(&mutex);

        buffer[in] = item;
        printf("Producer produced item %d at position %d\n", item, in);

        in = (in + 1) % BUFFER_SIZE;

        // Unlock critical section
        pthread_mutex_unlock(&mutex);

        // Signal that buffer has one more full slot
        sem_post(&full);

        sleep(1);
    }

    pthread_exit(NULL);
}

/* Consumer Thread */
void* consumer(void* arg) {
    int item;

    for(int i = 1; i <= ITEMS_TO_PRODUCE; i++) {

        // Wait if buffer is empty
        sem_wait(&full);

        // Lock critical section
        pthread_mutex_lock(&mutex);

        item = buffer[out];
        printf("Consumer consumed item %d from position %d\n", item, out);

        out = (out + 1) % BUFFER_SIZE;

        // Unlock critical section
        pthread_mutex_unlock(&mutex);

        // Signal that one empty slot is available
        sem_post(&empty);

        sleep(2);
    }

    pthread_exit(NULL);
}

int main() {

    pthread_t prodThread, consThread;

    // Initialize semaphores
    sem_init(&empty, 0, BUFFER_SIZE);
    sem_init(&full, 0, 0);

    // Initialize mutex
    pthread_mutex_init(&mutex, NULL);

    // Create threads
    pthread_create(&prodThread, NULL, producer, NULL);
    pthread_create(&consThread, NULL, consumer, NULL);

    // Wait for threads to finish
    pthread_join(prodThread, NULL);
    pthread_join(consThread, NULL);

    // Destroy semaphores and mutex
    sem_destroy(&empty);
    sem_destroy(&full);
    pthread_mutex_destroy(&mutex);

    printf("\nExecution completed successfully.\n");

    return 0;
}