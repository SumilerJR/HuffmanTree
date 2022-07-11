package DataStructure;

import com.alibaba.druid.util.StringUtils;
import com.demo.Employee;

import java.util.*;


class TreeNodePlus extends HuffmanTree{
    public int count;//count记录当前节点是该层的第几个节点
    public TreeNodePlus(HuffmanTree node,int count){
        this.left = node.left;
        this.right = node.right;
        this.weight = node.weight;
        this.count = count;
    }
}


public class HuffmanTree {
    int weight;//权值
    char data;//叶子结点才有值
    HuffmanTree left;//左子树
    HuffmanTree right;//右子树


    private static String emptyChar = " ";
    private static String leftChar = "╭";
    private static String linkChar = "─";
    private static String rightChar = "╮";
    private static String midChar = "┴";

    public HuffmanTree(){

    }

    public HuffmanTree(int weight, char data){
        this.weight = weight;
        this.data = data;
    }

    public HuffmanTree(int[] map){
        int length = map.length;
        HuffmanTree[] huffmanTrees = new HuffmanTree[length];
    }

    /**
     * 遍历哈夫曼树得到编码表
     * @param root
     * @param code
     * @param map
     */
    public void createMap(HuffmanTree root, StringBuilder code, String[] map){
        if(root == null)
            return;
        if(root.left == null && root.right == null){
            if(root.data != ' ')
                map[root.data - '@'] = code.toString();
            else
                map[0] = code.toString();
            return;
        }
        code.append(0);
        createMap(root.left, code, map);
        code.deleteCharAt(code.length() - 1);
        code.append(1);
        createMap(root.right, code, map);
        code.deleteCharAt(code.length() - 1);
    }

    /**
     * 根据map表对message进行哈夫曼编码
     * @param message
     * @param map
     * @return
     */
    public StringBuilder encoded(String message, String[] map) throws Exception {
        StringBuilder sb = new StringBuilder(500);
        for(int i = 0; i < message.length(); i++){
            char ch = message.charAt(i);
            if(ch == ' ')
                sb.append(map[0]);
            else if (ch >= 'A' && ch <= 'Z')
                sb.append(map[ch - '@']);
            else {
                throw new Exception();
            }
        }
        return sb;
    }

    /**
     * 根据已有的哈夫曼树huffmanTree对哈夫曼编码huffmanCode进行解码
     * @param huffmanCode
     * @param huffmanTree
     * @return
     */
    public StringBuilder decode(String huffmanCode, HuffmanTree huffmanTree) throws Exception {
        HuffmanTree root = huffmanTree;
        StringBuilder sb = new StringBuilder(500);
        for(int i = 0; i < huffmanCode.length(); i++){
            if(root == null){
                return null;
            }
            char ch = huffmanCode.charAt(i);
            if(root.left == null && root.left == null) {
                sb.append(root.data);
                root = huffmanTree;
            }
            if(ch == '0')
                root = root.left;
            else if (ch == '1')
                root = root.right;
            else
                throw new Exception();
        }
        if(root.left == null && root.left == null)
            sb.append(root.data);
        else
            return null;
        return sb;
    }


    /**
     * 纵向打印
     * @param root
     */
 /*   public static void print(HuffmanTree root) {
        printInOrder(root, 0, "", 10);
    }

    private static void printInOrder(HuffmanTree root, int height, String from, int len) {
        if (root == null) {
            return;
        }
        printInOrder(root.right, height + 1, "1╭─", len);
        String val = from + root.weight + ((root.left == null && root.right == null) ? "" : "＜");
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(root.left, height + 1, "0╰─", len);
    }

    private static String getSpace(int num) {
        String space = " ";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(space);
        }
        return stringBuilder.toString();
    }*/


    /**
     * 将链式存储的树转为顺序存储（下标从0开始）
     * @param root
     */
/*    public void getTreeMap (HuffmanTree root) {
        int height = this.getHeight(root);//获取树高
        int maxCapacity = (int) Math.pow(2, height);
        int[] treeMap = new int[maxCapacity];
        setTreeMap(root, treeMap, 0);//建立树图
        System.out.println(height);
        System.out.println(treeMap.length);
        for (int i = 1; i < treeMap.length; i++) {
            System.out.print(treeMap[i] + " ");
        }
        System.out.println();
//        int[] treeMap = {1,2,3,4,5,6,0,7,8};
//        showTree(treeMap);
//        print(root);
    }

    public int getHeight (HuffmanTree root) {
        return root == null ? 0 : (Math.max(getHeight(root.left), getHeight(root.right)) + 1);
    }


    public void setTreeMap (HuffmanTree root, int[] treeMap, int index) {
        if (root != null) {
            treeMap[index] = root.weight;
            setTreeMap(root.left, treeMap, index * 2 + 1);
            setTreeMap(root.right, treeMap, index * 2 + 2);
        }
    }*/


    //横向打印
/*    public static void showTree(int[] array){
        int hight = 1;// 层数
        int len = array.length;
        int p = 0;// 节点下标
        List<List<String>> list = new ArrayList<List<String>>();// 所有节点分层保存
        List<String> temp = new ArrayList<String>();// 当前层节点
        while ( p < len){
            int start = (int) Math.pow(2,hight-1) - 1;// 当前层起始节点下标
            int end = start + (int) (Math.pow(2,hight-1)) - 1;// 当前层结束节点下标
            temp.add(array[p] + "");
            p++;
            if(p > end || p >= len){ // 当前层循环结束 或者 所有节点循环结束
                list.add(temp);
                temp = new ArrayList<String>();
                if ( p < len){ // 最后⼀个节点层数不⽤累加
                    hight++; // 换层
                }
            }
        }

        int lastNum = (int)Math.pow(2,hight-1);// 满⼆叉树最后⼀层的节点数
        int lastLen = lastNum + (lastNum - 1); // 最后⼀层所占数组长度( 每个节点之间间隔⼀个位置)
        String[][] tree = new String[hight][lastLen];
        for (int i = 0,size = list.size(); i < size ; i ++) {
            List<String> strs = list.get(i);// 当前层所有节点
            int tempHight = hight - i;
            int start = (int) (Math.pow(2,tempHight-1) - 1);// 当前层起始节点下标
            for (String str : strs) {
                tree[i][start] = str;// 节点
                int gap = (int) Math.pow(2,tempHight);// 当前层节点间的间隔
                start = start + gap;// 下⼀个节点
            }
        }
        // 输出树
        for (int i = 0 ; i < hight ; i++){
            for(int j = 0 ; j < lastLen ; j ++){
                String str = tree[i][j];
//                System.out.format("%-2.2s", (StringUtils.isEmpty(str) || "0".equals(str)) ? "." : str);
                if (StringUtils.isEmpty(str) || "0".equals(str))
                    System.out.print("_");
                else
                    System.out.print(str);
            }
            System.out.println();
        }

    }*/



    /**
     * 横向打印
     * 以下树形结构可视化代码借鉴自：https://github.com/chaishilin/StructCharDraw
     */

    public static void drawTree(HuffmanTree root) {
        levelOrderTravel(root);
    }

    public static void printObjectList(Object[] objects){
        for(int k = 0; k < objects.length ; k+=1){
            System.out.print(objects[k]);
        }
        System.out.println("");
    }

    private static String[] parseString(String text) {
        text = text.replace('[', ' ');
        text = text.replace(']', ' ');
        text = text.trim();
        return text.split(",");
    }

    private static int getTreeHeight(HuffmanTree root) {
        if(root == null){
            return 0;
        }else if(root.left == null && root.right == null){
            return 1;
        }
        return 1+ Math.max(getTreeHeight(root.left),getTreeHeight(root.right));
    }

    private static int getPrintWidth(int height){
        int result = 1;
        for(int i = 1;i < height;i++){
            result = result*2+1;
        }
        return result;
    }

    private static int[] getABC(int index){
        int[] result = new int[3];
        result[0] = 0;
        result[1] = 3;
        result[2] = 1;
        for(int i = 2;i < index;i++){
            result[0] = result[0]*2+1;
            result[1] = 2*result[0]+3;
            result[2] = result[1] - 2;
        }
        return result;
    }

    private static void levelOrderTravel(HuffmanTree root){
        //中序遍历
        int height = getTreeHeight(root);
        int printLineWidth = getPrintWidth(height);
        List<TreeNodePlus> queue = new ArrayList<>();
        List<TreeNodePlus> queue2 = new ArrayList<>();
        queue.add(new TreeNodePlus(root,0));
        int level = 0;
        while(queue.size() > 0){
            //针对当前层数生成对应宽度的printList(1、2、4、8...)
            String[] printList = new String[(int) Math.pow(2,level)];
            for(int k = 0 ; k < printList.length;k++ ){
                printList[k] = emptyChar;
            }
            while (queue.size() > 0){
                //常规的中序遍历
                TreeNodePlus node = queue.remove(0);
                printList[node.count] = ""+node.weight;
                if(node.left != null){
                    queue2.add(new TreeNodePlus(node.left,node.count*2));
                }
                if(node.right != null){
                    queue2.add(new TreeNodePlus(node.right,node.count*2+1));
                }
            }
            level += 1;
            List<TreeNodePlus> temp = queue;
            queue = queue2;
            queue2 = temp;
            printTreeLine(printList,printLineWidth,height,level);
        }
    }

    /***
     *
     * @param printList 仅含节点的打印行
     * @param printLineWidth 实际打印行宽度
     * @param height 树高度
     * @param level 当前节点高度
     */
    public static void printTreeLine(String[] printList,int printLineWidth,int height,int level){
        //将仅包含数字的printList和实际宽度传入，生成实际的打印行
        String[] numList = getPrintNumLine(printList,printLineWidth,level);
        if(printList.length > 1){
            //如果不是打印根节点，则需要打印树枝
            String[] branchList = getPrintBranchLine(height+2-level,printLineWidth);
            //根据数字层是否有数字修改树枝层
            branchList = changeBranchByNum(branchList,numList);
            printObjectList(branchList);
        }
        printObjectList(numList);
    }

    private static String[] changeBranchByNum(String[] branchList,String[] numList){
        //对于树枝层的leftChar和rightChar对应的数字层数字为null的情况，修改leftChar或rightChar为emptyChar
        //正反各一次即可
        int i = 0;
        while(i < branchList.length){
            if(branchList[i] == leftChar && numList[i] == emptyChar){
                while(branchList[i] != midChar){
                    branchList[i++] = emptyChar;
                }
            }else{
                i++;
            }
        }
        i--;
        while(i > 0){
            if(branchList[i] == rightChar && numList[i] == emptyChar){
                while(branchList[i] != midChar ){
                    branchList[i--] = emptyChar;
                }
            }else{
                i--;
            }
        }
        //消除单独的加号
        for(int j = 1; j < numList.length-1;j ++){
            if(branchList[j-1] == emptyChar && branchList[j] == midChar && branchList[j+1] == emptyChar){
                branchList[j] = emptyChar;
            }
        }
        return branchList;
    }

    private static String[] getPrintNumLine(String[] printList,int width,int level){
        String[] result = new String[width];
        int breakNum = width;
        while(level > 1){
            breakNum /= 2;
            level-=1;
        }
        int count = 0;
        int countP = 0;
        while(count < breakNum/2){
            result[count++] = emptyChar;
        }
        while(count < width){
            if(count%(breakNum+1) != breakNum/2){
                result[count++] = emptyChar;
            }else{
                result[count++] = printList[countP++];
            }
        }
        return result;
    }

    private static String[] getPrintBranchLine(int index,int width){
        //a:开始的空格数，b:/--+--\的长度，c:间隔长度
        int[] abc = getABC(index);
        String[] line = new String[width];
        int count = 0;
        while(count < abc[0]){
            line[count++] = emptyChar;
        }
        String[] paserdLine = paserBranchLine(abc[1],abc[2]);
        int countP = 0;
        while(count < width){
            line[count++] = paserdLine[countP];
            countP = (countP + 1)%paserdLine.length;
        }
        return line;
    }

    private static String[] paserBranchLine(int b,int c){
        String[] result = new String[b+c];
        for(int i = 0;i< b+c;i++){
            if(i < b){
                if(i == 0){
                    result[i] = leftChar;
                }else if(i == b-1){
                    result[i] = rightChar;
                }else if(i == b/2){
                    result[i] = midChar;
                }else{
                    result[i] =linkChar;
                }
            }else{
                result[i] = emptyChar;
            }
        }
        return result;
    }


}
