import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

// 方法：用num保存要返回的结果，相当于iterator比num多移动了一格
// 对于peek()，直接返回num，不向后移动iterator

class PeekingIterator implements Iterator<Integer> {

    private Integer num;
    private Iterator<Integer> iterator;


	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.iterator = iterator;

        if (this.iterator.hasNext()) {
            num = iterator.next();
        }
	}
	
    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return num;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
        Integer toReturn = num;
        num = iterator.hasNext() ? iterator.next() : null;
        return toReturn;
	}
	
	@Override
	public boolean hasNext() {
        return num != null || iterator.hasNext();
	}
}