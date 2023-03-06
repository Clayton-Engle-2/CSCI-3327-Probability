package main.data.structures;


public class LinkedListNode<T> {
	
        private T data;
        private String tag;
        private LinkedListNode<T> next;
        private LinkedListNode<T> prev;

        public LinkedListNode(T data) {
            this.setData(data);
            this.setNext(null);
            this.setPrev(null);
        }
        
        public LinkedListNode(T data, String tag) {
        	this.setTag(tag);
            this.setData(data);
            this.setNext(null);
            this.setPrev(null);
        }

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public LinkedListNode<T> getNext() {
			return next;
		}

		public void setNext(LinkedListNode<T> next) {
			this.next = next;
		}

		public LinkedListNode<T> getPrev() {
			return prev;
		}

		public void setPrev(LinkedListNode<T> prev) {
			this.prev = prev;
		}

		public String getTag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}
    }


