public class Main {

    public abstract class ListItem {
        // write code here

        protected ListItem rightLink;
        protected ListItem leftLink;
        protected Object value;

        public ListItem(Object value) {
            this.value = value;
        }

        abstract ListItem next();
        abstract ListItem setNext(ListItem next);
        abstract ListItem previous();
        abstract ListItem setPrevious(ListItem next);
        abstract int compareTo(ListItem next);

        private Object getValue(){
            return value;
        }
        private void setValue(Object value){
            this.value = value;
        }


    }

    public class Node extends ListItem {

        public Node(Object value) {
            super(value);
        }

        @Override
        ListItem next() {
            return rightLink;
        }

        @Override
        ListItem setNext(ListItem next) {
            rightLink = next;
            return rightLink;
        }

        @Override
        ListItem previous() {
            return leftLink;
        }

        @Override
        ListItem setPrevious(ListItem next) {
            leftLink = next;
            return leftLink;
        }

        @Override
        int compareTo(ListItem item) {
            if (item != null) {
                return ((String) super.getValue()).compareTo((String) item.getValue());
            } else {
                return -1;
            }
        }
    }

    public class MyLinkedList implements NodeList {

        ListItem root;

        public MyLinkedList(ListItem root) {
            this.root = root;
        }

        @Override
        public ListItem getRoot() {
            return root;
        }

        @Override
        public boolean addItem(ListItem newItem) {

            if (this.root == null) {
                // the list was empty, so this item becomes the head of the list
                this.root = newItem;
                return true;
            }

            ListItem currentItem = this.root;
            while (currentItem != null) {
                int comparison = (currentItem.compareTo(newItem));
                if (comparison < 0) {
                    // newItem is greater than currentItem, move right if possible
                    if (currentItem.next() != null) {
                        currentItem = currentItem.next();
                    } else {
                        // there is no next, so insert at end of list
                        currentItem.setNext(newItem).setPrevious(currentItem);
                        return true;
                    }
                } else if (comparison > 0) {
                    // newItem is less than currentItem, insert before
                    if (currentItem.previous() != null) {
                        currentItem.previous().setNext(newItem).setPrevious(currentItem.previous());
                        newItem.setNext(currentItem).setPrevious(newItem);
                    } else {
                        // the node with a previous is the root
                        newItem.setNext(this.root).setPrevious(newItem);
                        this.root = newItem;
                    }
                    return true;
                } else {
                    // equal
                    return false;
                }
            }
            return false;
        }

        @Override
        public boolean removeItem(ListItem item) {

            if (item != null) {
                System.out.println("Deleting item " + item.getValue());
            }

            ListItem currentItem = this.root;
            while (currentItem != null) {
                int comparison = currentItem.compareTo(item);
                if (comparison == 0) {
                    // found the item to delete
                    if (currentItem == this.root) {
                        this.root = currentItem.next();
                    } else {
                        currentItem.previous().setNext(currentItem.next());
                        if (currentItem.next() != null) {
                            currentItem.next().setPrevious(currentItem.previous());
                        }
                    }
                    return true;
                } else if (comparison < 0) {
                    currentItem = currentItem.next();
                } else { // comparison > 0
                    // we are at an item greater than the one to be deleted
                    // so the item is not in the list
                    return false;
                }
            }
            // we have reached the end of the list
            // without finding the item to delete
            return false;
        }

        @Override
        public void traverse(ListItem root) {

            if (root == null) {
                System.out.println("The list is empty");
            } else {
                while (root != null) {
                    System.out.println(root.getValue());
                    root = root.next();
                }
            }
        }
    }

    public interface NodeList {

        ListItem getRoot();
        boolean addItem(ListItem item);
        boolean removeItem(ListItem item);
        void traverse(ListItem root);

    }

    public class SearchTree implements NodeList {

        private ListItem root = null;

        public SearchTree(ListItem root) {
            this.root = root;
        }

        @Override
        public ListItem getRoot() {
            return this.root;
        }

        @Override
        public boolean addItem(ListItem newItem) {

            if (this.root == null) {
                // the tree was empty, so our item becomes the head of the tree
                this.root = newItem;
                return true;
            }

            // otherwise, start comparing from the head of the tree
            ListItem currentItem = this.root;
            while (currentItem != null) {
                int comparison = (currentItem.compareTo(newItem));
                if (comparison < 0) {
                    // newItem is greater, move right if possible
                    if (currentItem.next() != null) {
                        currentItem = currentItem.next();
                    } else {
                        // there's no node to the right, so add at this point
                        currentItem.setNext(newItem);
                        return true;
                    }
                } else if (comparison > 0) {
                    // newItem is less, move left if possible
                    if (currentItem.previous() != null) {
                        currentItem = currentItem.previous();
                    } else {
                        // there's no node to the left, so add at this point
                        currentItem.setPrevious(newItem);
                        return true;
                    }
                } else {
                    // equal, so don't add
                    System.out.println(newItem.getValue() + " is already present");
                    return false;
                }
            }
            // we can't actually get here, but Java complains if there's no return
            return false;
        }

        @Override
        public boolean removeItem(ListItem item) {

            if (item != null) {
                System.out.println("Deleting item " + item.getValue());
            }
            ListItem currentItem = this.root;
            ListItem parentItem = currentItem;

            while (currentItem != null) {
                int comparison = (currentItem.compareTo(item));
                if (comparison < 0) {
                    parentItem = currentItem;
                    currentItem = currentItem.next();
                } else if (comparison > 0) {
                    parentItem = currentItem;
                    currentItem = currentItem.previous();
                } else {
                    // equal: we've found the item so remove it
                    performRemoval(currentItem, parentItem);
                    return true;
                }
            }
            return false;
        }


        private void performRemoval(ListItem item, ListItem parent) {
            // remove item from the tree
            if (item.next() == null) {
                // no right tree, so make parent point to left tree (which may be null)
                if (parent.next() == item) {
                    // item is right child of its parent
                    parent.setNext(item.previous());
                } else if (parent.previous() == item) {
                    // item is left child of its parent
                    parent.setPrevious(item.previous());
                } else {
                    // parent must be item, which means we were looking at the root of the tree
                    this.root = item.previous();
                }
            } else if (item.previous() == null) {
                // no left tree, so make parent point to right tree (which may be null)
                if (parent.next() == item) {
                    // item is right child of its parent
                    parent.setNext(item.next());
                } else if (parent.previous() == item) {
                    // item is left child of its parent
                    parent.setPrevious(item.next());
                } else {
                    // again, we are deleting the root
                    this.root = item.next();
                }
            } else {
                // neither left nor right are null, deletion is now a lot trickier!
                // From the right sub-tree, find the smallest value (i.e., the leftmost).
                ListItem current = item.next();
                ListItem leftmostParent = item;
                while (current.previous() != null) {
                    leftmostParent = current;
                    current = current.previous();
                }
                // Now put the smallest value into our node to be deleted
                item.setValue(current.getValue());
                // and delete the smallest
                if (leftmostParent == item) {
                    // there was no leftmost node, so 'current' points to the smallest
                    // node (the one that must now be deleted).
                    item.setNext(current.next());
                } else {
                    // set the smallest node's parent to point to
                    // the smallest node's right child (which may be null).
                    leftmostParent.setPrevious(current.next());
                }
            }
        }

        @Override
        public void traverse(ListItem root) {

            if (root != null) {
                traverse(root.previous()); // to the left of root
                System.out.println(root.getValue());
                traverse(root.next()); // to the right of root
            }
        }
    }


}