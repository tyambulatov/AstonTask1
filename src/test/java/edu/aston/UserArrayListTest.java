package edu.aston;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("UserArrayList is working when ...")
class UserArrayListTest {

    private UserArrayList<Integer> integerArrayList;
    private UserArrayList<String> stringArrayList;

    @BeforeEach
    void setUp() {
        integerArrayList = new UserArrayList<>();
        stringArrayList = new UserArrayList<>();
    }

    @DisplayName("add(T element) is working ...")
    @Nested
    class ForAdd {

        @ParameterizedTest
        @NullSource
        void add_null_element(Integer argument) {
            assertTrue(integerArrayList.add(argument));
            assertNull(integerArrayList.get(0));
        }

        @ParameterizedTest
        @EmptySource
        void add_empty_argument(String argument) {
            assertTrue(stringArrayList.add(argument));

            String actual = stringArrayList.get(0);
            assertTrue(actual.isEmpty());
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2})
        void add_one_element(Integer integer) {
            assertTrue(integerArrayList.add(integer));
            assertEquals(integer, integerArrayList.get(0));
        }

        @Test
        void add_more_than_ten_elements() {
            int[] array = RandomArrayGenerator.generate();

            for (int a : array) {
                assertTrue(integerArrayList.add(a));
            }

            String actual = integerArrayList.toString();

            assertEquals(Arrays.toString(array), actual);
        }

    }

    @DisplayName("add(int index, T element) is working ...")
    @Nested
    class ForAddByIndex {

        @Test
        void add_into_empty_array_expect_exception() {
            assertThrows(IndexOutOfBoundsException.class, () -> integerArrayList.add(1, 1));
        }

        @ParameterizedTest
        @ValueSource(ints = {-10, -2, -1, 2, 3, 10})
        void index_out_of_bound_expect_exception(Integer index) {
            integerArrayList.add(1);
            assertThrows(IndexOutOfBoundsException.class, () -> integerArrayList.add(index, 1));
        }

        @Test
        void valid_addition_into_index_0_array_size_ten() {
            String expected = "[11, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";

            for (int i = 1; i < 11; i++) {
                integerArrayList.add(i);
            }
            integerArrayList.add(0, 11);

            assertEquals(expected, integerArrayList.toString());
        }

        @Test
        void valid_addition_into_index_4_array_size_ten() {
            String expected = "[1, 2, 3, 4, 11, 5, 6, 7, 8, 9, 10]";

            for (int i = 1; i < 11; i++) {
                integerArrayList.add(i);
            }
            integerArrayList.add(4, 11);

            assertEquals(expected, integerArrayList.toString());
        }

        @Test
        void valid_addition_into_index_10_array_size_ten() {
            String expected = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]";

            for (int i = 1; i < 11; i++) {
                integerArrayList.add(i);
            }
            integerArrayList.add(10, 11);

            assertEquals(expected, integerArrayList.toString());
        }

        @Test
        void valid_multiple_additions() {
            String expected = "[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]";

            for (int i = 10; i >= 0; i--) {
                integerArrayList.add(0, i);
            }

            assertEquals(expected, integerArrayList.toString());
        }
    }


    @DisplayName("get() is working ...")
    @Nested
    class ForGet {
        @ParameterizedTest
        @ValueSource(ints = {-10, -1, 0, 1, 10})
        void empty_array_and_different_indexes(Integer index) {
            assertThrows(IndexOutOfBoundsException.class, () -> integerArrayList.get(index));
        }

        @ParameterizedTest
        @ValueSource(ints = {-2, -1, 1, 2})
        void size_one_array_and_different_indexes(Integer index) {
            integerArrayList.add(1);
            assertThrows(IndexOutOfBoundsException.class, () -> integerArrayList.get(index));
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
        void ok_get_from_array(Integer index) {
            for (int i = 1; i <= 10; i++) {
                integerArrayList.add(i);
            }
            assertEquals(index + 1, integerArrayList.get(index));
        }
    }


    @DisplayName("remove() is working ...")
    @Nested
    class ForRemove {
        @Test
        void remove_from_empty_array() {
            assertThrows(IndexOutOfBoundsException.class, () -> integerArrayList.remove(0));
        }

        @ParameterizedTest
        @ValueSource(ints = {-2, -1, 1, 2})
        void remove_with_different_indexes(Integer index) {
            integerArrayList.add(1);
            assertThrows(IndexOutOfBoundsException.class, () -> integerArrayList.remove(index));
        }

        @Test
        void remove_from_first_index() {
            String expected = "[2, 3, 4, 5]";

            for (int i = 1; i <= 5; i++) {
                integerArrayList.add(i);
            }
            assertEquals(integerArrayList.get(0), integerArrayList.remove(0));

            assertEquals(expected, integerArrayList.toString());
        }

        @Test
        void remove_from_last_index() {
            String expected = "[1, 2, 3, 4]";

            for (int i = 1; i <= 5; i++) {
                integerArrayList.add(i);
            }
            assertEquals(integerArrayList.get(4), integerArrayList.remove(4));

            assertEquals(expected, integerArrayList.toString());
        }
    }

    @Test
    void clear() {
    }

    @DisplayName("clear() is working ...")
    @Nested
    class ForClear {
        @Test
        void clear_array() {
            for (int i = 0; i < 10; i++) {
                integerArrayList.add(i);
            }
            integerArrayList.clear();
            assertThrows(IndexOutOfBoundsException.class, () -> integerArrayList.get(0));
            assertEquals(0, integerArrayList.size());
        }
    }

    @DisplayName("sort() is working ...")
    @Nested
    class ForSort {
        @Test
        void sort_zero_size_array() {
            integerArrayList.sort(Integer::compare);
            assertThrows(IndexOutOfBoundsException.class, () -> integerArrayList.get(0));
            assertEquals(0, integerArrayList.size());
        }

        @Test
        void sort_array_size_one() {
            integerArrayList.add(1);
            integerArrayList.sort(Integer::compare);
            assertEquals(1, integerArrayList.get(0));
        }

        @Test
        void sort_null_element() {
            integerArrayList.add(1);
            integerArrayList.add(null);
            integerArrayList.add(0);
            assertThrows(NullPointerException.class, () -> integerArrayList.sort(Integer::compare));
        }

        @Test
        void sort_array_two_elements() {
            integerArrayList.add(1);
            integerArrayList.add(2);
            integerArrayList.sort(Integer::compare);
            assertEquals("[1, 2]", integerArrayList.toString());
            integerArrayList.clear();

            integerArrayList.add(1);
            integerArrayList.add(1);
            integerArrayList.sort(Integer::compare);
            assertEquals("[1, 1]", integerArrayList.toString());
            integerArrayList.clear();


            integerArrayList.add(2);
            integerArrayList.add(1);
            integerArrayList.sort(Integer::compare);
            assertEquals("[1, 2]", integerArrayList.toString());
        }

        @Test
        void array_sort_with_not_unique_elements() {
            int[] array = {1, 6, 4, 6, 3, 4, 7};

            for (int a : array) {
                integerArrayList.add(a);
            }
            integerArrayList.sort(Integer::compare);

            Arrays.sort(array);
            String expected = Arrays.toString(array);
            assertEquals(expected, integerArrayList.toString());
        }

        @Test
        void array_sort_unique_elements() {
            int[] array = {1, 5, 2, 6, 3, 4, 7};

            for (int a : array) {
                integerArrayList.add(a);
            }
            integerArrayList.sort(Integer::compare);

            Arrays.sort(array);
            String expected = Arrays.toString(array);
            assertEquals(expected, integerArrayList.toString());
        }
    }
}