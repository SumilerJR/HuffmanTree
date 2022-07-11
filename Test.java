package DataStructure;

import java.util.*;

public class Test {
    public static void main(String[] args) {
        int[] data = {186,64,13,22,32,103,21,15,47,57,1,5,32,20,57,63,15,1,48,51,80,23,8,18,1,16,1};

        HuffmanTree[] huffmanTrees = new HuffmanTree[data.length + 1];
        huffmanTrees[0] = new HuffmanTree();
        huffmanTrees[1] = new HuffmanTree(data[0],' ');
        for(int i = 2; i < huffmanTrees.length; i++){
            huffmanTrees[i] = new HuffmanTree(data[i - 1], (char) (63 + i));//构建n个哈夫曼树
        }


        HuffmanTreeMinHeap huffmanTreeMinHeap = new HuffmanTreeMinHeap(huffmanTrees);//哈夫曼树的最小堆
        huffmanTreeMinHeap.BuildHeap();//构建最小堆

        //构建完整的哈夫曼树
        int size = huffmanTreeMinHeap.size;
        for(int i = 1; i < size; i++){
            HuffmanTree node = new HuffmanTree();
            node.left = huffmanTreeMinHeap.delete();
//            if (!Character.isUpperCase(node.left.data) && node.left.data != ' ')
//                node.left.data = (char) ('0' + node.left.data);
            node.right = huffmanTreeMinHeap.delete();
//            if (!Character.isUpperCase(node.right.data) && node.right.data != ' ')
//                node.right.data = (char) ('1' + node.right.data);
            node.weight = node.left.weight + node.right.weight;
            huffmanTreeMinHeap.insert(node);
        }

        HuffmanTree huffmanTree = huffmanTreeMinHeap.delete();//删除最小堆中最后一棵哈夫曼树，得整个哈夫曼树的根节点
        String[] map = new String[27];

        HuffmanTree root = huffmanTree;
        StringBuilder code = new StringBuilder();
        huffmanTree.createMap(root, code, map);


        //打印编码表
        System.out.println("↓↓↓↓↓由所给的数据得出该哈夫曼树对应的编码表如下↓↓↓↓↓");
        //纵向打印
//        System.out.println("空格:" + map[0]);
//        for(int i = 1; i < map.length; i++) {
//            System.out.print((char)(i + '@') + ":\t");
//            System.out.println(String.format("|%-20s|", map[i]));
//        }
        //横向打印
        System.out.print(String.format("%-11s", "空格"));
        for(int i = 1; i < map.length; i++) {
            System.out.print(String.format("%-12s", (char)(i + '@')));
        }
        System.out.println();
        System.out.print(String.format("%-12s", map[0]));
        for(int i = 1; i < map.length; i++) {
            System.out.print(String.format("%-12s", map[i]));
        }
        System.out.println();

        //打印哈夫曼树
        System.out.println("↓↓↓↓↓由所给的数据得出该哈夫曼树的树形图如下↓↓↓↓↓");
//        HuffmanTree.print(root);
//        HuffmanTree.showTree();
//        huffmanTree.getTreeMap(root);
        HuffmanTree.drawTree(root);

        Scanner input = new Scanner(System.in);
        System.out.print("请输入要编码的报文：");
        String message = input.nextLine();
        //调用编码方法
        StringBuilder sb = null;
        while (true) {
            try {
                sb = huffmanTree.encoded(message, map);
                break;
            } catch (Exception e) {
                System.out.println("报文只能由空格和大写字母组成！");
                System.out.print("请重新输入要编码的报文：");
                message = input.nextLine();
            }
        }
        if(sb == null)
            System.out.println("编码失败！");
        else {
            System.out.println("该报文的哈夫曼编码为：" + sb.toString());
            System.out.println("编码长度为：" + sb.length());
        }

        System.out.print("请输入要解码的哈夫曼编码：");
        String huffmanCode = input.nextLine();
        while (true) {
            try {
                sb = huffmanTree.decode(huffmanCode, huffmanTree);
                break;
            } catch (Exception e) {
                System.out.println("编码只能由0和1组成！");
                System.out.print("请重新输入编码：");
                huffmanCode = input.nextLine();
            }
        }
        if(sb == null)
            System.out.println("解码失败！");
        else
            System.out.print("该哈夫曼编码解码为：" + sb.toString());
    }

}
