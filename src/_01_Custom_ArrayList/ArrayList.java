package _01_Custom_ArrayList;

@SuppressWarnings("unchecked")

public class ArrayList <T>{
		T[] arr;
	public ArrayList() {
		this.arr = (T[]) new Object [1];
	}
	
	public T get(int loc) throws IndexOutOfBoundsException {
		
		return arr[loc];
	}
	
	public void add(T val) {
		T[] arr1 = (T[]) new Object[arr.length + 1];
		for (int i = 0; i < arr.length; i++) {
			arr1[i] = arr[i];
		}
		arr1[arr.length - 1] = val;
		
		arr = arr1;
		
	}
	
	public void insert(int loc, T val) throws IndexOutOfBoundsException {
		T[] arr1 = (T[]) new Object[arr.length + 1];
		for (int i = 0; i < loc; i++) {
			arr1[i] = arr[i];
		}
		arr1[loc] = val;
		for (int i = loc; i < arr.length; i++) {
			arr1[i + 1] = arr[i];
		}
		
		arr = arr1;
		
	}
	
	public void set(int loc, T val) throws IndexOutOfBoundsException {
		T[] arr1 = (T[]) new Object[arr.length + 1];
		for (int i = 0; i < loc; i++) {
			arr1[i] = arr[i];
		}
		arr1[loc] = val;
		for (int i = loc + 1; i < arr.length; i++) {
			arr1[i] = arr[i];
		}
		
		arr = arr1;
	}
	
	public void remove(int loc) throws IndexOutOfBoundsException {
		T[] arr1 = (T[]) new Object[arr.length - 1];
		for (int i = 0; i < loc; i++) {
			arr1[i] = arr[i];
		}
		for (int i = loc + 1; i < arr.length; i++) {
			arr1[i - 1] = arr[i];
		}
		
		arr = arr1;
	}
	
	public boolean contains(T val) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == val) {
				return true;
			}
		}
		return false;
	}

	public int size() {
		return arr.length - 1;
	}
}