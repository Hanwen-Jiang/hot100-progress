@SuppressWarnings("all")
public class TreeNode {
int val;
TreeNode left;
TreeNode right;
TreeNode() {}
TreeNode(int val) { this.val = val; }
TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
}
}

//IMPORTANT!! Submit Code Region Begin(Do not remove this line)
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root==null) return new ArrayList<>();
        Queue<TreeNode> list = new LinkedList<>();
        List<List<Integer>> results = new ArrayList<>();
        list.offer(root);
        while (!list.isEmpty()) {
            int size = list.size();
            List<Integer> integers = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.poll();
                integers.add(node.val);
                if (node.left != null) list.offer(node.left);
                if (node.right != null) list.offer(node.right);
            }
            results.add(integers);
        }
        return results;
    }
}
//IMPORTANT!! Submit Code Region End(Do not remove this line)

void main() {
    Solution solution = new Solution();
    {
        String[] rawInputs = new String[] { "[3,9,20,null,null,15,7]" };
        String expected = "[[3],[9,20],[15,7]]";
        runCase(solution, "levelOrder", rawInputs, expected);
    }
    {
        String[] rawInputs = new String[] { "[1]" };
        String expected = "[[1]]";
        runCase(solution, "levelOrder", rawInputs, expected);
    }
    {
        String[] rawInputs = new String[] { "[]" };
        String expected = "[]";
        runCase(solution, "levelOrder", rawInputs, expected);
    }
}
@SuppressWarnings("all")
static void runCase(Object solution, String methodName, String[] rawInputs, String expected) {
    try {
        java.lang.reflect.Method target = null;
        for (java.lang.reflect.Method method : solution.getClass().getDeclaredMethods()) {
            if (method.getName().equals(methodName)) {
                target = method;
                break;
            }
        }
        if (target == null) {
            throw new RuntimeException("Method not found: " + methodName);
        }
        Class<?>[] parameterTypes = target.getParameterTypes();
        Object[] args;
        if (isIntersectionCase(methodName, parameterTypes, rawInputs)) {
            args = buildIntersectionArgs(rawInputs, parameterTypes);
        } else if (isCycleListCase(methodName, parameterTypes, rawInputs)) {
            args = buildCycleListArgs(rawInputs, parameterTypes);
        } else {
            if (rawInputs.length < parameterTypes.length) {
                throw new RuntimeException("Not enough input lines. need=" + parameterTypes.length + " got=" + rawInputs.length);
            }
            args = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                args[i] = parseValue(rawInputs[i], parameterTypes[i]);
            }
        }
        Object out = target.invoke(solution, args);
        Object displayOut = target.getReturnType() == void.class ? pickVoidOutput(args) : formatSpecialOutput(methodName, out);
        System.out.println("input:");
        for (String rawInput : rawInputs) {
            System.out.println(rawInput);
        }
        System.out.println("output: " + toPrettyString(displayOut));
        System.out.println("expected output: " + expected);
        System.out.println();
    } catch (Exception e) {
        e.printStackTrace();
        System.out.println();
    }
}

static Object pickVoidOutput(Object[] args) {
    if (args == null || args.length == 0) {
        return null;
    }
    for (Object arg : args) {
        if (arg == null) {
            continue;
        }
        Class<?> clazz = arg.getClass();
        if (clazz.isArray() || arg instanceof java.util.Collection || arg instanceof java.util.Map) {
            return arg;
        }
        if (isListNodeObject(arg) || isTreeNodeObject(arg) || isRandomNodeObject(arg)) {
            return arg;
        }
    }
    return args.length == 1 ? args[0] : args;
}

static String toPrettyString(Object value) {
    if (value == null) {
        return "null";
    }
    Class<?> clazz = value.getClass();
    if (isRandomNodeObject(value)) {
        return randomNodeToString(value);
    }
    if (isListNodeObject(value)) {
        return listNodeToString(value);
    }
    if (isTreeNodeObject(value)) {
        return treeNodeToString(value);
    }
    if (value instanceof java.util.Map<?, ?>) {
        return mapToString((java.util.Map<?, ?>) value);
    }
    if (value instanceof java.util.Collection<?>) {
        return collectionToString((java.util.Collection<?>) value);
    }
    if (clazz.isArray()) {
        return arrayToString(value);
    }
    if (value instanceof String) {
        return String.valueOf(value);
    }
    return String.valueOf(value);
}

static String arrayToString(Object array) {
    int length = java.lang.reflect.Array.getLength(array);
    StringBuilder builder = new StringBuilder();
    builder.append("[");
    for (int i = 0; i < length; i++) {
        if (i > 0) {
            builder.append(", ");
        }
        builder.append(toPrettyString(java.lang.reflect.Array.get(array, i)));
    }
    builder.append("]");
    return builder.toString();
}

static String collectionToString(java.util.Collection<?> values) {
    StringBuilder builder = new StringBuilder();
    builder.append("[");
    int index = 0;
    for (Object value : values) {
        if (index++ > 0) {
            builder.append(", ");
        }
        builder.append(toPrettyString(value));
    }
    builder.append("]");
    return builder.toString();
}

static String mapToString(java.util.Map<?, ?> values) {
    StringBuilder builder = new StringBuilder();
    builder.append("{");
    int index = 0;
    for (java.util.Map.Entry<?, ?> entry : values.entrySet()) {
        if (index++ > 0) {
            builder.append(", ");
        }
        builder.append(String.valueOf(entry.getKey()));
        builder.append(": ");
        builder.append(toPrettyString(entry.getValue()));
    }
    builder.append("}");
    return builder.toString();
}

static Object parseValue(String rawInput, Class<?> type) {
    try {
        String value = normalizeInputValue(rawInput);
        if ("null".equals(value)) {
            return null;
        }
        if (isRandomNodeType(type)) {
            return initRandomListNode(value, type);
        }
        if (isListNodeType(type)) {
            return initListNode(value, type);
        }
        if (isTreeNodeType(type)) {
            return initTreeNode(value, type);
        }
        if (type.isArray()) {
            return initArrayValue(value, type);
        }
        if (java.util.List.class.isAssignableFrom(type)) {
            return initListValue(value);
        }
        if (java.util.Map.class.isAssignableFrom(type)) {
            return initMapValue(value);
        }
        return initScalarValue(value, type);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

static Object initScalarValue(String rawInput, Class<?> type) {
    String value = normalizeInputValue(rawInput);
    if ("null".equals(value)) {
        return null;
    }
    if (type == String.class) {
        return unquote(value);
    }
    if (type == char.class || type == Character.class) {
        String unquoted = unquote(value);
        if (unquoted.length() != 1) {
            throw new RuntimeException("char expected, got: " + rawInput);
        }
        return unquoted.charAt(0);
    }
    if (type == int.class || type == Integer.class) {
        return Integer.parseInt(value);
    }
    if (type == long.class || type == Long.class) {
        return Long.parseLong(value);
    }
    if (type == double.class || type == Double.class) {
        return Double.parseDouble(value);
    }
    if (type == float.class || type == Float.class) {
        return Float.parseFloat(value);
    }
    if (type == boolean.class || type == Boolean.class) {
        return Boolean.parseBoolean(value);
    }
    if (type == short.class || type == Short.class) {
        return Short.parseShort(value);
    }
    if (type == byte.class || type == Byte.class) {
        return Byte.parseByte(value);
    }
    return parseJsonLike(value);
}

static Object initArrayValue(String rawInput, Class<?> arrayType) {
    String value = normalizeInputValue(rawInput);
    Class<?> componentType = arrayType.getComponentType();
    java.util.List<String> items = splitTopLevelArrayItems(value);
    Object array = java.lang.reflect.Array.newInstance(componentType, items.size());
    for (int i = 0; i < items.size(); i++) {
        Object element = parseValue(items.get(i), componentType);
        java.lang.reflect.Array.set(array, i, element);
    }
    return array;
}

static Object initListValue(String rawInput) {
    String value = normalizeInputValue(rawInput);
    java.util.List<String> items = splitTopLevelArrayItems(value);
    java.util.ArrayList<Object> list = new java.util.ArrayList<>();
    for (String item : items) {
        list.add(parseJsonLike(item));
    }
    return list;
}

static Object initMapValue(String rawInput) {
    return parseMapLike(normalizeInputValue(rawInput));
}

static Object initListNode(String rawInput, Class<?> nodeType) throws Exception {
    String value = normalizeInputValue(rawInput);
    java.util.List<String> items = splitTopLevelArrayItems(value);
    if (items.isEmpty() || (items.size() == 1 && "null".equals(items.get(0).trim()))) {
        return null;
    }
    Object head = null;
    Object prev = null;
    for (String item : items) {
        Object node = createNodeObject(nodeType, item.trim());
        if (head == null) {
            head = node;
        }
        if (prev != null) {
            setNext(prev, node);
        }
        prev = node;
    }
    return head;
}

static Object initTreeNode(String rawInput, Class<?> nodeType) throws Exception {
    String value = normalizeInputValue(rawInput);
    java.util.List<String> items = splitTopLevelArrayItems(value);
    if (items.isEmpty() || (items.size() == 1 && "null".equals(items.get(0).trim()))) {
        return null;
    }
    java.util.ArrayList<Object> nodes = new java.util.ArrayList<>();
    for (String item : items) {
        String token = item.trim();
        if ("null".equals(token)) {
            nodes.add(null);
        } else {
            nodes.add(createNodeObject(nodeType, token));
        }
    }
    int childIndex = 1;
    for (int i = 0; i < nodes.size() && childIndex < nodes.size(); i++) {
        Object current = nodes.get(i);
        if (current == null) {
            continue;
        }
        if (childIndex < nodes.size()) {
            setChild(current, "left", nodes.get(childIndex++));
        }
        if (childIndex < nodes.size()) {
            setChild(current, "right", nodes.get(childIndex++));
        }
    }
    return nodes.get(0);
}

static Object initRandomListNode(String rawInput, Class<?> nodeType) throws Exception {
    String value = normalizeInputValue(rawInput);
    java.util.List<String> items = splitTopLevelArrayItems(value);
    if (items.isEmpty()) {
        return null;
    }
    java.util.ArrayList<Object> nodes = new java.util.ArrayList<>();
    java.util.ArrayList<Integer> randomIndexes = new java.util.ArrayList<>();
    for (String item : items) {
        java.util.List<String> pair = splitTopLevelArrayItems(item);
        if (pair.isEmpty()) {
            continue;
        }
        Object node = createNodeObject(nodeType, normalizeInputValue(pair.get(0)));
        nodes.add(node);
        Integer randomIndex = null;
        if (pair.size() > 1) {
            String randomText = normalizeInputValue(pair.get(1));
            if (!"null".equals(randomText) && randomText.length() > 0) {
                randomIndex = Integer.parseInt(randomText);
            }
        }
        randomIndexes.add(randomIndex);
    }
    for (int i = 0; i < nodes.size(); i++) {
        Object node = nodes.get(i);
        setFieldValue(node, "next", i + 1 < nodes.size() ? nodes.get(i + 1) : null);
        Integer randomIndex = randomIndexes.get(i);
        Object randomNode = null;
        if (randomIndex != null) {
            if (randomIndex < 0 || randomIndex >= nodes.size()) {
                throw new RuntimeException("random index out of range: " + randomIndex);
            }
            randomNode = nodes.get(randomIndex);
        }
        setFieldValue(node, "random", randomNode);
    }
    return nodes.isEmpty() ? null : nodes.get(0);
}


static TreeNode initTreeNode(String rawInput) {
    try {
        return (TreeNode) initTreeNode(rawInput, TreeNode.class);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}

static String treeNodeToString(TreeNode root) {
    return treeNodeToString((Object) root);
}


static boolean isIntersectionCase(String methodName, Class<?>[] parameterTypes, String[] rawInputs) {
    return "getIntersectionNode".equals(methodName)
            && parameterTypes != null
            && parameterTypes.length == 2
            && rawInputs != null
            && rawInputs.length >= 5
            && isListNodeType(parameterTypes[0])
            && isListNodeType(parameterTypes[1]);
}

static boolean isCycleListCase(String methodName, Class<?>[] parameterTypes, String[] rawInputs) {
    if (!"hasCycle".equals(methodName) && !"detectCycle".equals(methodName)) {
        return false;
    }
    return parameterTypes != null
            && parameterTypes.length == 1
            && rawInputs != null
            && rawInputs.length >= 2
            && isListNodeType(parameterTypes[0]);
}

static Object[] buildIntersectionArgs(String[] rawInputs, Class<?>[] parameterTypes) throws Exception {
    String intersectValueText = normalizeInputValue(rawInputs[0]);
    String listAText = normalizeInputValue(rawInputs[1]);
    String listBText = normalizeInputValue(rawInputs[2]);
    int skipA = Integer.parseInt(normalizeInputValue(rawInputs[3]));
    int skipB = Integer.parseInt(normalizeInputValue(rawInputs[4]));
    Class<?> nodeType = parameterTypes[0];
    Object headA = initListNode(listAText, nodeType);
    Object headB = initListNode(listBText, nodeType);
    int intersectValue = 0;
    try {
        intersectValue = Integer.parseInt(intersectValueText);
    } catch (Exception ignore) {
    }
    if (intersectValue != 0) {
        Object nodeA = getNodeAt(headA, skipA);
        Object nodeB = getNodeAt(headB, skipB);
        if (nodeA == null) {
            throw new RuntimeException("skipA out of range: " + skipA);
        }
        if (nodeB == null) {
            throw new RuntimeException("skipB out of range: " + skipB);
        }
        Object prevB = getPrevNode(headB, nodeB);
        if (prevB == null) {
            headB = nodeA;
        } else {
            setNext(prevB, nodeA);
        }
    }
    return new Object[] { headA, headB };
}

static Object[] buildCycleListArgs(String[] rawInputs, Class<?>[] parameterTypes) throws Exception {
    String listText = normalizeInputValue(rawInputs[0]);
    int pos = Integer.parseInt(normalizeInputValue(rawInputs[1]));
    Object head = initListNode(listText, parameterTypes[0]);
    if (pos >= 0) {
        Object cycleEntry = getNodeAt(head, pos);
        if (cycleEntry == null) {
            throw new RuntimeException("pos out of range: " + pos);
        }
        Object tail = getTailNode(head);
        if (tail != null) {
            setNext(tail, cycleEntry);
        }
    }
    return new Object[] { head };
}

static Object getNodeAt(Object head, int idx) throws Exception {
    Object current = head;
    int step = 0;
    while (current != null && step < idx) {
        current = getNext(current);
        step++;
    }
    return current;
}

static Object getTailNode(Object head) throws Exception {
    Object current = head;
    Object prev = null;
    java.util.HashSet<Object> seen = new java.util.HashSet<>();
    while (current != null && seen.add(current)) {
        prev = current;
        current = getNext(current);
    }
    return prev;
}

static Object getPrevNode(Object head, Object target) throws Exception {
    if (head == null || head == target) {
        return null;
    }
    Object current = head;
    while (current != null) {
        Object next = getNext(current);
        if (next == target) {
            return current;
        }
        current = next;
    }
    return null;
}

static Object getNext(Object node) throws Exception {
    return getFieldValue(node, "next", true);
}

static Object formatSpecialOutput(String methodName, Object out) {
    if ("getIntersectionNode".equals(methodName)) {
        if (out == null) {
            return "No intersection";
        }
        Integer value = tryGetNodeVal(out);
        return value == null ? "Intersected" : "Intersected at '" + value + "'";
    }
    return out;
}

static boolean isListNodeType(Class<?> type) {
    if (type == null) {
        return false;
    }
    String name = type.getName();
    return (name.endsWith("ListNode") || name.contains("$ListNode")) && hasField(type, "next");
}

static boolean isListNodeObject(Object value) {
    return value != null && isListNodeType(value.getClass());
}

static String listNodeToString(Object head) {
    try {
        java.util.ArrayList<String> values = new java.util.ArrayList<>();
        java.util.HashSet<Object> seen = new java.util.HashSet<>();
        Object current = head;
        while (current != null && seen.add(current)) {
            Integer value = tryGetNodeVal(current);
            values.add(value == null ? "null" : String.valueOf(value));
            current = getNext(current);
        }
        if (current != null) {
            values.add("...");
        }
        return "[" + String.join(", ", values) + "]";
    } catch (Exception e) {
        return "ListNode";
    }
}

static boolean isTreeNodeType(Class<?> type) {
    if (type == null) {
        return false;
    }
    String name = type.getName();
    return (name.endsWith("TreeNode") || name.contains("$TreeNode")) && hasField(type, "left") && hasField(type, "right");
}

static boolean isTreeNodeObject(Object value) {
    return value != null && isTreeNodeType(value.getClass());
}

static String treeNodeToString(Object root) {
    try {
        if (root == null) {
            return "[]";
        }
        java.util.ArrayList<String> values = new java.util.ArrayList<>();
        java.util.LinkedList<Object> queue = new java.util.LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Object current = queue.removeFirst();
            if (current == null) {
                values.add("null");
                continue;
            }
            Integer value = tryGetNodeVal(current);
            values.add(value == null ? "null" : String.valueOf(value));
            queue.add(getChild(current, "left"));
            queue.add(getChild(current, "right"));
        }
        int end = values.size() - 1;
        while (end >= 0 && "null".equals(values.get(end))) {
            end--;
        }
        java.util.ArrayList<String> trimmed = new java.util.ArrayList<>();
        for (int i = 0; i <= end; i++) {
            trimmed.add(values.get(i));
        }
        return "[" + String.join(", ", trimmed) + "]";
    } catch (Exception e) {
        return "TreeNode";
    }
}

static boolean isRandomNodeType(Class<?> type) {
    return type != null
            && !isTreeNodeType(type)
            && hasField(type, "val")
            && hasField(type, "next")
            && hasField(type, "random");
}

static boolean isRandomNodeObject(Object value) {
    return value != null && isRandomNodeType(value.getClass());
}

static String randomNodeToString(Object head) {
    try {
        if (head == null) {
            return "[]";
        }
        java.util.ArrayList<Object> nodes = new java.util.ArrayList<>();
        java.util.IdentityHashMap<Object, Integer> indexMap = new java.util.IdentityHashMap<>();
        Object current = head;
        while (current != null && !indexMap.containsKey(current)) {
            indexMap.put(current, nodes.size());
            nodes.add(current);
            current = getNext(current);
        }
        java.util.ArrayList<String> values = new java.util.ArrayList<>();
        for (Object node : nodes) {
            Integer nodeValue = tryGetNodeVal(node);
            Object random = getFieldValue(node, "random", false);
            String randomText = "null";
            if (random != null) {
                Integer randomIndex = indexMap.get(random);
                randomText = randomIndex == null ? "?" : String.valueOf(randomIndex);
            }
            values.add("[" + (nodeValue == null ? "null" : nodeValue) + "," + randomText + "]");
        }
        if (current != null) {
            values.add("[...]");
        }
        return "[" + String.join(", ", values) + "]";
    } catch (Exception e) {
        return "Node";
    }
}

static Integer tryGetNodeVal(Object node) {
    try {
        java.lang.reflect.Field field = findField(node.getClass(), "val");
        if (field == null) {
            return null;
        }
        field.setAccessible(true);
        Object value = field.get(node);
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        if (value != null) {
            try {
                return Integer.parseInt(String.valueOf(value));
            } catch (Exception ignore) {
            }
        }
        return null;
    } catch (Exception e) {
        return null;
    }
}

static final Object NO_VALUE = new Object();

static Object createNodeObject(Class<?> nodeType, String rawValue) throws Exception {
    String value = normalizeInputValue(rawValue);
    if ("null".equals(value)) {
        return null;
    }
    java.lang.reflect.Constructor<?>[] constructors = nodeType.getDeclaredConstructors();
    java.util.Arrays.sort(constructors, new java.util.Comparator<java.lang.reflect.Constructor<?>>() {
        public int compare(java.lang.reflect.Constructor<?> left, java.lang.reflect.Constructor<?> right) {
            return left.getParameterCount() - right.getParameterCount();
        }
    });
    Class<?> enclosing = nodeType.getEnclosingClass();
    boolean nonStaticInner = enclosing != null && !java.lang.reflect.Modifier.isStatic(nodeType.getModifiers());
    Object outer = null;
    if (nonStaticInner) {
        outer = createOuterInstance(enclosing);
    }
    for (java.lang.reflect.Constructor<?> constructor : constructors) {
        constructor.setAccessible(true);
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        try {
            if (!nonStaticInner) {
                if (parameterTypes.length == 1) {
                    Object arg = convertScalarForNodeValue(value, parameterTypes[0]);
                    if (arg != NO_VALUE) {
                        return constructor.newInstance(arg);
                    }
                }
            } else if (parameterTypes.length == 2 && parameterTypes[0] == enclosing) {
                Object arg = convertScalarForNodeValue(value, parameterTypes[1]);
                if (arg != NO_VALUE) {
                    return constructor.newInstance(outer, arg);
                }
            }
        } catch (Exception ignore) {
        }
    }
    for (java.lang.reflect.Constructor<?> constructor : constructors) {
        constructor.setAccessible(true);
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        try {
            Object node;
            if (!nonStaticInner) {
                if (parameterTypes.length != 0) {
                    continue;
                }
                node = constructor.newInstance();
            } else {
                if (parameterTypes.length != 1 || parameterTypes[0] != enclosing) {
                    continue;
                }
                node = constructor.newInstance(outer);
            }
            setVal(node, value);
            return node;
        } catch (Exception ignore) {
        }
    }
    throw new RuntimeException("Cannot construct node for type: " + nodeType.getName());
}

static Object convertScalarForNodeValue(String rawInput, Class<?> targetType) {
    try {
        String value = normalizeInputValue(rawInput);
        if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value);
        }
        if (targetType == long.class || targetType == Long.class) {
            return Long.parseLong(value);
        }
        if (targetType == short.class || targetType == Short.class) {
            return Short.parseShort(value);
        }
        if (targetType == byte.class || targetType == Byte.class) {
            return Byte.parseByte(value);
        }
        if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(value);
        }
        if (targetType == float.class || targetType == Float.class) {
            return Float.parseFloat(value);
        }
        if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        }
        if (targetType == char.class || targetType == Character.class) {
            String unquoted = unquote(value);
            return unquoted.length() == 1 ? unquoted.charAt(0) : NO_VALUE;
        }
        if (targetType == String.class) {
            return unquote(value);
        }
        if (targetType == Object.class) {
            try {
                return Integer.parseInt(value);
            } catch (Exception ignore) {
            }
            try {
                return Long.parseLong(value);
            } catch (Exception ignore) {
            }
            return unquote(value);
        }
        return NO_VALUE;
    } catch (Exception e) {
        return NO_VALUE;
    }
}

static void setVal(Object node, String rawValue) throws Exception {
    java.lang.reflect.Field field = findField(node.getClass(), "val");
    if (field == null) {
        throw new RuntimeException("No val field found in " + node.getClass().getName());
    }
    field.setAccessible(true);
    Object value = convertScalarForNodeValue(rawValue, field.getType());
    if (value == NO_VALUE) {
        throw new RuntimeException("Cannot convert value '" + rawValue + "' to " + field.getType().getName());
    }
    field.set(node, value);
}

static void setNext(Object node, Object next) throws Exception {
    setFieldValue(node, "next", next);
}

static void setChild(Object node, String childName, Object child) throws Exception {
    setFieldValue(node, childName, child);
}

static Object getChild(Object node, String childName) throws Exception {
    return getFieldValue(node, childName, false);
}

static void setFieldValue(Object target, String fieldName, Object value) throws Exception {
    java.lang.reflect.Field field = findField(target.getClass(), fieldName);
    if (field == null) {
        throw new RuntimeException("No " + fieldName + " field found in " + target.getClass().getName());
    }
    field.setAccessible(true);
    field.set(target, value);
}

static Object getFieldValue(Object target, String fieldName, boolean required) throws Exception {
    if (target == null) {
        return null;
    }
    java.lang.reflect.Field field = findField(target.getClass(), fieldName);
    if (field == null) {
        if (required) {
            throw new RuntimeException("No " + fieldName + " field found in " + target.getClass().getName());
        }
        return null;
    }
    field.setAccessible(true);
    return field.get(target);
}

static boolean hasField(Class<?> type, String fieldName) {
    return findField(type, fieldName) != null;
}

static java.lang.reflect.Field findField(Class<?> type, String name) {
    Class<?> current = type;
    while (current != null) {
        try {
            return current.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            current = current.getSuperclass();
        }
    }
    return null;
}

static Object createOuterInstance(Class<?> enclosing) throws Exception {
    java.lang.reflect.Constructor<?> constructor = enclosing.getDeclaredConstructor();
    constructor.setAccessible(true);
    return constructor.newInstance();
}

static String normalizeInputValue(String rawInput) {
    if (rawInput == null) {
        return "";
    }
    String value = rawInput.trim();
    String[] prefixes = new String[] { "输入：", "输入:", "input:", "Input:" };
    for (String prefix : prefixes) {
        if (value.startsWith(prefix)) {
            value = value.substring(prefix.length()).trim();
            break;
        }
    }
    int assignIndex = findTopLevelAssignment(value);
    if (assignIndex > 0) {
        String left = value.substring(0, assignIndex).trim();
        if (left.matches("[A-Za-z_][A-Za-z0-9_]*")) {
            value = value.substring(assignIndex + 1).trim();
        }
    }
    return value;
}

static String unquote(String value) {
    value = value.trim();
    if ((value.startsWith("\"") && value.endsWith("\"")) || (value.startsWith("'") && value.endsWith("'"))) {
        value = value.substring(1, value.length() - 1);
    }
    return value;
}

static Object parseJsonLike(String rawInput) {
    String value = normalizeInputValue(rawInput);
    if ("null".equals(value)) {
        return null;
    }
    if ("true".equals(value) || "false".equals(value)) {
        return Boolean.parseBoolean(value);
    }
    if (value.startsWith("\"") || value.startsWith("'")) {
        return unquote(value);
    }
    if (value.startsWith("[")) {
        java.util.List<String> items = splitTopLevelArrayItems(value);
        java.util.ArrayList<Object> list = new java.util.ArrayList<>();
        for (String item : items) {
            list.add(parseJsonLike(item));
        }
        return list;
    }
    if (value.startsWith("{")) {
        return parseMapLike(value);
    }
    if (value.matches("[-+]?\\d+")) {
        try {
            return Integer.parseInt(value);
        } catch (Exception ignore) {
        }
        try {
            return Long.parseLong(value);
        } catch (Exception ignore) {
        }
    }
    if (value.matches("[-+]?\\d*\\.\\d+([eE][-+]?\\d+)?") || value.matches("[-+]?\\d+([eE][-+]?\\d+)?")) {
        try {
            return Double.parseDouble(value);
        } catch (Exception ignore) {
        }
    }
    return value;
}

static java.util.List<String> splitTopLevelArrayItems(String rawInput) {
    String value = normalizeInputValue(rawInput);
    if (!value.startsWith("[") || !value.endsWith("]")) {
        java.util.ArrayList<String> single = new java.util.ArrayList<>();
        if (!value.isEmpty()) {
            single.add(value);
        }
        return single;
    }
    String body = value.substring(1, value.length() - 1).trim();
    java.util.ArrayList<String> items = new java.util.ArrayList<>();
    if (body.isEmpty()) {
        return items;
    }
    int depth = 0;
    boolean inString = false;
    char stringQuote = 0;
    int start = 0;
    for (int i = 0; i < body.length(); i++) {
        char ch = body.charAt(i);
        if (inString) {
            if (ch == stringQuote && body.charAt(Math.max(i - 1, 0)) != '\\') {
                inString = false;
            }
            continue;
        }
        if (ch == '"' || ch == '\'') {
            inString = true;
            stringQuote = ch;
            continue;
        }
        if (ch == '[' || ch == '{' || ch == '(') {
            depth++;
        } else if (ch == ']' || ch == '}' || ch == ')') {
            depth--;
        } else if (ch == ',' && depth == 0) {
            items.add(body.substring(start, i).trim());
            start = i + 1;
        }
    }
    items.add(body.substring(start).trim());
    return items;
}

static java.util.Map<String, Object> parseMapLike(String rawInput) {
    String value = normalizeInputValue(rawInput);
    if (!value.startsWith("{") || !value.endsWith("}")) {
        throw new RuntimeException("map expected: " + rawInput);
    }
    String body = value.substring(1, value.length() - 1).trim();
    java.util.LinkedHashMap<String, Object> map = new java.util.LinkedHashMap<>();
    if (body.isEmpty()) {
        return map;
    }
    java.util.List<String> parts = splitTopLevelComma(body);
    for (String part : parts) {
        int colonIndex = findTopLevelColon(part);
        if (colonIndex < 0) {
            throw new RuntimeException("bad map entry: " + part);
        }
        String key = unquote(part.substring(0, colonIndex).trim());
        String itemValue = part.substring(colonIndex + 1).trim();
        map.put(key, parseJsonLike(itemValue));
    }
    return map;
}

static java.util.List<String> splitTopLevelComma(String body) {
    java.util.ArrayList<String> items = new java.util.ArrayList<>();
    int depth = 0;
    boolean inString = false;
    char stringQuote = 0;
    int start = 0;
    for (int i = 0; i < body.length(); i++) {
        char ch = body.charAt(i);
        if (inString) {
            if (ch == stringQuote && body.charAt(Math.max(i - 1, 0)) != '\\') {
                inString = false;
            }
            continue;
        }
        if (ch == '"' || ch == '\'') {
            inString = true;
            stringQuote = ch;
            continue;
        }
        if (ch == '[' || ch == '{' || ch == '(') {
            depth++;
        } else if (ch == ']' || ch == '}' || ch == ')') {
            depth--;
        } else if (ch == ',' && depth == 0) {
            items.add(body.substring(start, i).trim());
            start = i + 1;
        }
    }
    items.add(body.substring(start).trim());
    return items;
}

static int findTopLevelColon(String value) {
    int depth = 0;
    boolean inString = false;
    char stringQuote = 0;
    for (int i = 0; i < value.length(); i++) {
        char ch = value.charAt(i);
        if (inString) {
            if (ch == stringQuote && value.charAt(Math.max(i - 1, 0)) != '\\') {
                inString = false;
            }
            continue;
        }
        if (ch == '"' || ch == '\'') {
            inString = true;
            stringQuote = ch;
            continue;
        }
        if (ch == '[' || ch == '{' || ch == '(') {
            depth++;
        } else if (ch == ']' || ch == '}' || ch == ')') {
            depth--;
        } else if (ch == ':' && depth == 0) {
            return i;
        }
    }
    return -1;
}

static int findTopLevelAssignment(String value) {
    int depth = 0;
    boolean inString = false;
    char stringQuote = 0;
    for (int i = 0; i < value.length(); i++) {
        char ch = value.charAt(i);
        if (inString) {
            if (ch == stringQuote && value.charAt(Math.max(i - 1, 0)) != '\\') {
                inString = false;
            }
            continue;
        }
        if (ch == '"' || ch == '\'') {
            inString = true;
            stringQuote = ch;
            continue;
        }
        if (ch == '[' || ch == '{' || ch == '(') {
            depth++;
        } else if (ch == ']' || ch == '}' || ch == ')') {
            depth--;
        } else if (ch == '=' && depth == 0) {
            char prev = i > 0 ? value.charAt(i - 1) : 0;
            char next = i + 1 < value.length() ? value.charAt(i + 1) : 0;
            if (prev != '=' && prev != '!' && prev != '<' && prev != '>' && next != '=') {
                return i;
            }
        }
    }
    return -1;
}