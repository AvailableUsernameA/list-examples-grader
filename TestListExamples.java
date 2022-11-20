import java.util.ArrayList;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.*;

class checkLongString implements StringChecker {
  public boolean checkString(String s) {
    return s.length()>=5;
  }
}

public class TestListExamples {
  // Write your grading tests here!
  @Test(timeout = 100)
  public void testMerge() {
    List<String> input1 = new ArrayList<>();
    input1.add("a");
    input1.add("b");
    List<String> input2 = new ArrayList<>();
    input2.add("c");
    List<String> result = ListExamples.merge(input1, input2);
    List<String> expect = new ArrayList<>();
    expect.add("a");
    expect.add("b");
    expect.add("c");
    assertEquals(expect, result);

    List<String> input2_1 = new ArrayList<>();
    input1.add("a");
    input1.add("b");
    List<String> input2_2 = new ArrayList<>();
    input2.add("a");
    input2.add("b");
    List<String> result2 = ListExamples.merge(input2_1, input2_2);
    List<String> expect2 = new ArrayList<>();
    expect.add("a");
    expect.add("a");
    expect.add("b");
    expect.add("b");
    assertEquals(expect2, result2);
  }

  @Test(timeout = 100)
  public void testFilter() {
    List<String> input1 = new ArrayList<String>();
    input1.add("apple");
    input1.add("hi");
    input1.add("banana");
    List<String> result1 = ListExamples.filter(input1, new checkLongString());
    List<String> expect1 = new ArrayList<String>();
    expect1.add("apple");
    expect1.add("banana");
    assertEquals(expect1, result1);
  }

  /*@Test(timeout = 100)
  public void testFilterNotChangeOrigin() {
    List<String> input1 = new ArrayList<String>();
    input1.add("apple");
    input1.add("hi");
    input1.add("banana");
    ListExamples.filter(input1, new checkLongString());
    List<String> expect1 = new ArrayList<String>();
    expect1.add("apple");
    expect1.add("hi");
    expect1.add("banana");
    assertEquals(expect1, input1);
  }*/
}