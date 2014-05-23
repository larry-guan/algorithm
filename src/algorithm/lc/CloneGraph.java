/*

题目：
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.


OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/


思路：

首先做一次BFS遍历，将所有结点创建出来。并且要保存在一个可以在短时间读取的结构中。map是个不错的结构并且每个结点label唯一，因此我们可以用label来作为索引。

第二次遍历的时候可以快速地找出结点并构造图的关系。

*/

package algorithm.lc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class CloneGraph {

	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null)
            return null;
 
        LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = 
                                   new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
 
        UndirectedGraphNode newHead = new UndirectedGraphNode(node.label);
 
        queue.add(node);
        map.put(node, newHead);
 
        while(!queue.isEmpty()){
            UndirectedGraphNode curr = queue.pop();
            ArrayList<UndirectedGraphNode> currNeighbors = curr.neighbors; 
 
            for(UndirectedGraphNode aNeighbor: currNeighbors){
                if(!map.containsKey(aNeighbor)){
                    UndirectedGraphNode copy = new UndirectedGraphNode(aNeighbor.label);
                    map.put(aNeighbor,copy);
                    map.get(curr).neighbors.add(copy);
                    queue.add(aNeighbor);
                }else{
                    map.get(curr).neighbors.add(map.get(aNeighbor));
                }
            }
 
        }
        return newHead;
    }
    
    public static void main(String[] args){
    	UndirectedGraphNode n1 = new UndirectedGraphNode("1");
    	n1.neighbors = new ArrayList<UndirectedGraphNode>();
    	
    	UndirectedGraphNode n2 = new UndirectedGraphNode("2");
    	n2.neighbors = new ArrayList<UndirectedGraphNode>();
    	
    	UndirectedGraphNode n3 = new UndirectedGraphNode("3");
    	n3.neighbors = new ArrayList<UndirectedGraphNode>();
    	
    	UndirectedGraphNode n4 = new UndirectedGraphNode("4");
    	n4.neighbors = new ArrayList<UndirectedGraphNode>();

		n1.neighbors.add(n2);
		n1.neighbors.add(n3);
		n2.neighbors.add(n1);
		n2.neighbors.add(n3);
		n2.neighbors.add(n4);
		n3.neighbors.add(n1);
		n3.neighbors.add(n2);
		n3.neighbors.add(n4);
		n4.neighbors.add(n2);
		n4.neighbors.add(n3);
		
		UndirectedGraphNode returnNode = new CloneGraph().cloneGraph(n1);
    }

}

class UndirectedGraphNode{
	public UndirectedGraphNode(String label){
		this.label = label;
	}
	String label;
	ArrayList<UndirectedGraphNode> neighbors;
}
