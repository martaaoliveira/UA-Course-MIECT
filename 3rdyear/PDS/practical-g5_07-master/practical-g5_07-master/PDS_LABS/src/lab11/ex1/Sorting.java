package lab11.ex1;
 
class BubbleSort implements SortingStrategy {
    public void sort(Phone[] phones, String criteria) {
        int n = phones.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparePhones(phones[j], phones[j + 1], criteria) > 0) {
                    Phone temp = phones[j];
                    phones[j] = phones[j + 1];
                    phones[j + 1] = temp;
                }
            }
        }
        System.out.println("The array has been sorted with BubbleSort!");
    }

    private int comparePhones(Phone phone1, Phone phone2, String criteria) {
        switch (criteria) {
            case "cpu":
                return phone1.getCpu().compareTo(phone2.getCpu());
            case "memory":
                return Integer.compare(phone1.getMemory(), phone2.getMemory());
            case "camera":
                return phone1.getCamera().compareTo(phone2.getCamera());
            case "price":
                return Double.compare(phone1.getPrice(), phone2.getPrice());
            default:
                throw new IllegalArgumentException("Invalid property to sort by.");
        }
    }
}

class InsertionSort implements SortingStrategy {
    public void sort(Phone[] phones, String criteria) {
        int n = phones.length;
        for (int i = 1; i < n; ++i) {
            Phone key = phones[i];
            int j = i - 1;

            while (j >= 0 && comparePhones(phones[j], key, criteria) > 0) {
                phones[j + 1] = phones[j];
                j = j - 1;
            }
            phones[j + 1] = key;
        }

        System.out.println("The array has been sorted with InsertionSort!");
    }

    private int comparePhones(Phone phone1, Phone phone2, String criteria) {
        switch (criteria) {
            case "cpu":
                return phone1.getCpu().compareTo(phone2.getCpu());
            case "memory":
                return Integer.compare(phone1.getMemory(), phone2.getMemory());
            case "camera":
                return phone1.getCamera().compareTo(phone2.getCamera());
            case "price":
                return Double.compare(phone1.getPrice(), phone2.getPrice());
            default:
                throw new IllegalArgumentException("Invalid property to sort by.");
        }
    }
}

class QuickSort implements SortingStrategy {
    public void sort(Phone[] phones, String criteria) {
        quickSort(phones, 0, phones.length - 1, criteria);
        System.out.println("The array has been sorted with QuickSort!");
    }

    private void swap(Phone[] arr, int i, int j) {
        Phone temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private int partition(Phone[] arr, int low, int high, String criteria) {
        Phone pivot = arr[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (comparePhones(arr[j], pivot, criteria) < 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return (i + 1);
    }

    private void quickSort(Phone[] arr, int low, int high, String criteria) {
        if (low < high) {
            int pi = partition(arr, low, high, criteria);
            quickSort(arr, low, pi - 1, criteria);
            quickSort(arr, pi + 1, high, criteria);
        }
    }

    private int comparePhones(Phone phone1, Phone phone2, String criteria) {
        switch (criteria) {
            case "cpu":
                return phone1.getCpu().compareTo(phone2.getCpu());
            case "memory":
                return Integer.compare(phone1.getMemory(), phone2.getMemory());
            case "camera":
                return phone1.getCamera().compareTo(phone2.getCamera());
            case "price":
                return Double.compare(phone1.getPrice(), phone2.getPrice());
            default:
                throw new IllegalArgumentException("Invalid property to sort by.");
        }
    }
}

class MergeSort implements SortingStrategy {
    public void sort(Phone[] phones, String criteria) {
        sortM(phones, 0, phones.length - 1, criteria);
        System.out.println("The array has been sorted with MergeSort!");
    }

    private void merge(Phone[] arr, int l, int m, int r, String criteria) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Phone[] L = new Phone[n1];
        Phone[] R = new Phone[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (comparePhones(L[i], R[j], criteria) <= 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    private void sortM(Phone[] arr, int l, int r, String criteria) {
        if (l < r) {
            int m = l + (r - l) / 2;
            sortM(arr, l, m, criteria);
            sortM(arr, m + 1, r, criteria);
            merge(arr, l, m, r, criteria);
        }
    }

    private int comparePhones(Phone phone1, Phone phone2, String criteria) {
        switch (criteria) {
            case "cpu":
                return phone1.getCpu().compareTo(phone2.getCpu());
            case "memory":
                return Integer.compare(phone1.getMemory(), phone2.getMemory());
            case "camera":
                return phone1.getCamera().compareTo(phone2.getCamera());
            case "price":
                return Double.compare(phone1.getPrice(), phone2.getPrice());
            default:
                throw new IllegalArgumentException("Invalid property to sort by.");
        }
    }
}