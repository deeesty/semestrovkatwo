public class AVLTree<T extends Comparable<T>> {

    private Node<T> root;
    private int size;

    public int add(T data) {
        Node<T> newNode = new Node<>(data);
        if (root == null) {
            root = newNode;
        } else {
            insert(newNode, root);
        }
        size++;
        return 1;
    }

    private void insert(Node<T> newNode, Node<T> currentNode) {
        if (newNode.data.compareTo(currentNode.data) < 0) {
            if (currentNode.left == null) {
                currentNode.left = newNode;
            } else {
                insert(newNode, currentNode.left);
            }
        } else {
            if (currentNode.right == null) {
                currentNode.right = newNode;
            } else {
                insert(newNode, currentNode.right);
            }
        }

        updateHeight(currentNode);
        balance(currentNode);
    }

    public int remove(T data) {
        root = delete(root, data);
        size--;
        return 1;
    }

    private Node<T> delete(Node<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.data) < 0) {
            node.left = delete(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = delete(node.right, data);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            node.data = findMin(node.right).data;
            node.right = delete(node.right, node.data);
        }

        updateHeight(node);
        balance(node);

        return node;
    }

    public int contains(T data) {
        return find(root, data) != null ? 1 : 0;
    }

    private Node<T> find(Node<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.data) < 0) {
            return find(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            return find(node.right, data);
        } else {
            return node;
        }
    }

    private void updateHeight(Node<T> node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private int getHeight(Node<T> node) {
        return node != null ? node.height : 0;
    }

    private void balance(Node<T> node) {
        int balanceFactor = getBalanceFactor(node);

        // балансировка если левая ветка тяжелая
        if (balanceFactor > 1) {
            // лл
            if (getBalanceFactor(node.left) >= 0) {
                rightRotate(node);
            }
            // лр
            else {
                leftRotate(node.left);
                rightRotate(node);
            }
        }
        // правое тяжелое
        else if (balanceFactor < -1) {
            // Right-Right Case
            if (getBalanceFactor(node.right) <= 0) {
                leftRotate(node);
            }
            // рл
            else {
                rightRotate(node.right);
                leftRotate(node);
            }
        }
    }

    private Node<T> rightRotate(Node<T> node) {
        Node<T> leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;
    }

    private Node<T> leftRotate(Node<T> node) {
        Node<T> rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;
    }

    private int getBalanceFactor(Node<T> node) {
        return getHeight(node.left) - getHeight(node.right);
    }

    private Node<T> findMin(Node<T> node) {
        if (node.left == null) {
            return node;
        } else {
            return findMin(node.left);
        }
    }


    //вершина дерева
    private static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;
        private int height;

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    }

    public int size() {
        return size;
    }
}

