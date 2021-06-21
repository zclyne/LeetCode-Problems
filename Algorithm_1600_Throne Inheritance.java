import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


// 本质上是多叉树的前序遍历
// 对于dead的人，不能加入遍历的结果中，但是他的children仍然需要遍历

class ThroneInheritance {
    private String kingName;
    private Map<String, ArrayList<String>> childrenMap;
    private Set<String> dead;

    public ThroneInheritance(String kingName) {
        this.kingName = kingName;
        this.childrenMap = new HashMap<>();
        this.dead = new HashSet<>();
    }
    
    public void birth(String parentName, String childName) {
        ArrayList<String> children = this.childrenMap.getOrDefault(parentName, new ArrayList<>());
        children.add(childName);
        this.childrenMap.put(parentName, children);
    }
    
    public void death(String name) {
        this.dead.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        List<String> inheritanceOrder = new LinkedList<>();
        this.preOrder(this.kingName, inheritanceOrder);
        return inheritanceOrder;
    }

    private void preOrder(String curName, List<String> curOrder) {
        if (!this.dead.contains(curName)) {
            curOrder.add(curName);
        }
        ArrayList<String> children = this.childrenMap.getOrDefault(curName, new ArrayList<>());
        for (String child : children) {
            this.preOrder(child, curOrder);
        }
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */