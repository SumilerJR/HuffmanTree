package DataStructure;

public class HuffmanTreeMinHeap {
    HuffmanTree[] huffmanTrees;
    int size;
    int capacity;

    public HuffmanTreeMinHeap(){

    }
    public HuffmanTreeMinHeap(int maxSize) {
        this.capacity = maxSize;
        this.size = 0;
        huffmanTrees[0].weight = Integer.MIN_VALUE;
    }

    public HuffmanTreeMinHeap(HuffmanTree[] huffmanTrees){
        this.huffmanTrees = huffmanTrees;
        this.capacity = huffmanTrees.length - 1;
        this.size = capacity;
    }


    public void BuildHeap(){
        for(int i = size / 2; i > 0; i--){
            PercDown(i);
        }

    }

    public void PercDown(int p) {
        int parent, child;
        HuffmanTree x;
        x = huffmanTrees[p];
        for(parent = p; parent * 2 <= size; parent = child){
            child = parent * 2;
            if((child != size) && (huffmanTrees[child].weight > huffmanTrees[child + 1].weight))
                child++;
            if(x.weight <= huffmanTrees[child].weight)
                break;
            else
                huffmanTrees[parent] = huffmanTrees[child];
        }
        huffmanTrees[parent] = x;
    }


    /**
     * 判断是否满堆
     * @return
     */
    public boolean isFull(){
        return size == capacity;
    }

    /**
     * 判断是否为空堆
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 最小堆插入
     * @param x
     * @return
     */
    public boolean insert(HuffmanTree x){
        int i;
        if(isFull()){
            System.out.println("最小堆已满");
            return false;
        }
        i = ++size;
        while(huffmanTrees[i / 2].weight > x.weight){
            huffmanTrees[i] = huffmanTrees[i / 2];
            i /= 2;
        }
        huffmanTrees[i] = x;
        return true;
    }

    /**
     * 删除
     * @return
     */
    HuffmanTree delete(){
        int parent, child;
        HuffmanTree minItem, x;
        if(isEmpty()){
            System.out.println("最小堆为空");
            return null;
        }
        minItem = huffmanTrees[1];
        x = huffmanTrees[size--];
        for(parent = 1; parent * 2 <= size; parent = child){
            child = parent * 2;
            if((child != size) && (huffmanTrees[child].weight > huffmanTrees[child + 1].weight))
                child++;
            if(x.weight <= huffmanTrees[child].weight)
                break;
            else
                huffmanTrees[parent] = huffmanTrees[child];
        }
        huffmanTrees[parent] = x;
        return minItem;
    }



}
