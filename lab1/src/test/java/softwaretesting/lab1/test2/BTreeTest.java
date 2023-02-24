package softwaretesting.lab1.test2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import softwaretesting.lab1.task2.BTree;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BTreeTest {
    BTree tree;

    @BeforeEach
    public void fixture() {
        tree = new BTree();
        tree.insert(3);
        tree.insert(-1);
        tree.insert(-4);
        tree.insert(999);
    }

    private static class searchArgs implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(0, false),
                    Arguments.of(-3, false),
                    Arguments.of(999, true),
                    Arguments.of(-1, true)
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(searchArgs.class)
    public void contains(int val, boolean expected) {
        Assertions.assertEquals(expected, tree.contains(val));
    }

    @ParameterizedTest
    @ValueSource(ints = { 999, -21, -4, -999 })
    public void removing(int val) {
        if (tree.contains(val)){
            assertTrue(tree.delete(val));
            assertFalse(tree.contains(val));
        }
        else {
            assertFalse(tree.delete(val));
            assertFalse(tree.contains(val));
        }
    }

    @ParameterizedTest
    @ValueSource(ints = { -2, -21, -999 })
    public void inserting(int val) {
        assertFalse(tree.contains(val));
        tree.insert(val);
        assertTrue(tree.contains(val));
    }

    @Test
    public void print() {
        tree.print();
    }
}
