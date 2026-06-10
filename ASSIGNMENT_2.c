#include <stdio.h>
#include <stdlib.h>

// O(1) – Constant Space
void constantSpace() {
    int a = 10, b = 20, c;
    c = a + b;
}

// O(n) – Linear Space
void linearSpace(int n) {
    int *arr = (int *)malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        arr[i] = i;
    }
    free(arr);
}

// O(n^2) – Quadratic Space (Simulated)
void quadraticSpace(int n) {
    volatile int x = 0;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            x++;
}

int main() {

    printf("\nInput Size\tO(1) Space\tO(n) Space\tO(n^2) Space\n");
    printf("------------------------------------------------------------\n");

    for (int n = 10000; n <= 90000; n += 10000) {

        constantSpace();
        linearSpace(n);
        quadraticSpace(n);

        printf("%d\t\tConstant\tLinear\t\tQuadratic\n", n);
    }

    return 0;
}
