/**
 * ***************************************************************************** Copyright (c) 2018
 * Fraunhofer IEM, Paderborn, Germany. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * <p>SPDX-License-Identifier: EPL-2.0
 *
 * <p>Contributors: Johannes Spaeth - initial API and implementation
 * *****************************************************************************
 */
package boomerang.example;

public class BoomerangExampleTarget1 {
  public static void main(String... args) {
    ClassWithField a = new ClassWithField();   //Object of classwithField a is created
    a.field = new ObjectOfInterest();
    ClassWithField b = a;                      //Alias pointer object b is created pointing to same reference as a
    NestedClassWithField n = new NestedClassWithField();      //Object n of NestesClassWithField is created
    n.nested = a;                              //object n points to reference of classWithField
    staticCallOnFile(a, n);                    //call to staticCallOnFile method
  }

  private static void staticCallOnFile(ClassWithField x, NestedClassWithField n) {
    ObjectOfInterest queryVariable = x.field;
    // The analysis triggers a query for the following variable
    queryFor(queryVariable);
  }
  private static void queryFor(ObjectOfInterest queryVariable) {
    System.out.println(queryVariable);
  }
  public static class ClassWithField {
    public ObjectOfInterest field;
  }
  public static class ObjectOfInterest {}
  public static class NestedClassWithField {
    public ClassWithField nested;
  }
}
