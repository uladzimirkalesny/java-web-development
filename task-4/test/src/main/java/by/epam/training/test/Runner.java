package by.epam.training.test;

import by.epam.training.library.impl.entity.EntityImpl;
import by.epam.training.library.impl.list.ArrayList;
import by.epam.training.library.impl.list.LinkedList;
import by.epam.training.library.impl.map.ArrayMap;
import by.epam.training.library.impl.map.ListMap;
import by.epam.training.library.impl.queue.ArrayStack;
import by.epam.training.library.impl.queue.ListQueue;
import by.epam.training.library.interfaces.Entity;
import by.epam.training.library.interfaces.Iterator;
import by.epam.training.library.interfaces.List;
import by.epam.training.library.interfaces.Map;
import by.epam.training.library.interfaces.Queue;
import by.epam.training.library.interfaces.Stack;
import by.epam.training.test.entity.User;
import by.epam.training.test.enums.SexType;

public class Runner {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(null);
        System.out.println(list.size());

        Queue<Integer> queue = new ListQueue<>();
        queue.push(1222);
        queue.push(2222);
        Integer pull = queue.pull();
        System.out.println(pull);

        list.trim();
        System.out.println(list.size());

        System.out.println(list.remove(0));
        System.out.println(list.size());

        Map<String, Integer> map = new ArrayMap<>();
        map.set("one", 1);
        List values = map.getValues();


        Stack<Integer> stack = new ArrayStack<>();
        stack.push(1111);
        stack.push(112);
        stack.sort((o1, o2) -> o2 - o1);

        Iterator<Integer> iterator1 = stack.getIterator();
        while (iterator1.hasNext()) {
            System.out.println(iterator1.getNext());
        }

        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.add(21);
        linkedList.add(22);
        linkedList.add(22);
        linkedList.add(23);

        System.out.println(linkedList.remove(3));
        linkedList.setMaxSize(1);
        System.out.println(linkedList.size());
        list.addAll(linkedList);

        Iterator iterator = list.getIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.getNext());
        }

        User user =
                new User("Uladzimir",
                        "uladzimirkalesny@gmail.com", 27, new EntityImpl<>(SexType.MALE, "i'm wonder man"));
        System.out.println(user);

        User x = new User("X", "axm@mail.ru", 12, new EntityImpl<>(SexType.FEMALE, "i'm kind of girl"));
        Map<String, User> users = new ListMap<>();
        users.set("Uladzimir", user);
        users.set("X", x);

        Iterator iterator2 = users.getValues().getIterator();
        while (iterator2.hasNext()) {
            System.out.println(iterator2.getNext());
        }
        Entity<String, Integer> tryCode = new EntityImpl<>("try", 1);
        String key = tryCode.getKey();
        Integer value = tryCode.getValue();
        System.out.println(key);
        System.out.println(value);

        List<User> userList = new ArrayList<>();
        int add = userList.add(user);
        System.out.println(add);

        Iterator userIterator = userList.getIterator();
        while (userIterator.hasNext()) {
            System.out.println(userIterator.getNext());
        }

        Map<String, User> userMap = new ArrayMap<>();
        userMap.set("test woman", x);

        User test_woman = userMap.get("test woman");
        System.out.println(test_woman);
        int clear = userMap.clear();
        System.out.println(clear);

        int size = userMap.size();
        System.out.println(size);

    }
}
