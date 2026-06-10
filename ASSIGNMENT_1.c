#include <stdio.h>
#include <time.h>

// O(1) - Constant Time
void constantTime() {
    int x = 10;
    x = x * x;
}

// O(n) - Linear Time
void linearTime(int n) {
    volatile int sum = 0;   // volatile prevents compiler optimization
    for (int i = 0; i < n; i++) {
        sum += i;
    }
}

// O(n^2) - Quadratic Time
void quadraticTime(int n) {
    volatile int sum = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            sum += i + j;
        }
    }
}

int main() {
    int sizes[] = {1000, 10000, 20000, 30000, 50000, 60000, 70000, 80000, 90000};
    int len = sizeof(sizes) / sizeof(sizes[0]);

    printf("\nInput Size\tO(1) Time\tO(n) Time\tO(n^2) Time\n");
    printf("-------------------------------------------------------------\n");

    for (int i = 0; i < len; i++) {
        int n = sizes[i];
        clock_t start, end;

        // Measure O(1)
        start = clock();
        constantTime();
        end = clock();
        double time_o1 = (double)(end - start) / CLOCKS_PER_SEC;

        // Measure O(n)
        start = clock();
        linearTime(n);
        end = clock();
        double time_on = (double)(end - start) / CLOCKS_PER_SEC;

        // Measure O(n^2)
        start = clock();
        quadraticTime(n);
        end = clock();
        double time_on2 = (double)(end - start) / CLOCKS_PER_SEC;

        printf("%d\t\t%.6f\t%.6f\t%.6f\n", n, time_o1, time_on, time_on2);
    }

    return 0;
}
