package boomerang.example;

import boomerang.BackwardQuery;
import boomerang.Boomerang;
import boomerang.DefaultBoomerangOptions;
import boomerang.Query;
import boomerang.results.BackwardBoomerangResults;
import boomerang.scene.AnalysisScope;
import boomerang.scene.ControlFlowGraph.Edge;
import boomerang.scene.SootDataFlowScope;
import boomerang.scene.Statement;
import boomerang.scene.Val;
import boomerang.scene.jimple.BoomerangPretransformer;
import boomerang.scene.jimple.SootCallGraph;
import soot.*;
import soot.options.Options;
import wpds.impl.Weight;

import java.io.File;
import java.util.*;


public class ExampleMain3 {
    public static void main(String[] args) {
        String sootClassPath = getSootClassPath();
        String mainClass = "boomerang.example.BoomerangExample3.java";
        setupSoot(sootClassPath, mainClass);
        //analyze();
    }

    private static String getSootClassPath() {
        String sootClassPath =
                System.getProperty("user.dir") + File.separator + "target" + File.separator + "classes";
        File classPathDir = new File(sootClassPath);
        if (!classPathDir.exists()) {
            // We haven't found our target folder
            // Check if it is in the boomerangPDS in user dir; this should work in IntelliJ
            sootClassPath =
                    System.getProperty("user.dir")
                            + File.separator
                            + "boomerangPDS"
                            + File.separator
                            + "target"
                            + File.separator
                            + "classes";
            classPathDir = new File(sootClassPath);
            if (!classPathDir.exists()) {
                // We haven't found our bytecode anyway, notify now instead of starting analysis anyway
                throw new RuntimeException("Classpath could not be found.");
            }
        }
        return sootClassPath;

    }

    private static void setupSoot(String sootClassPath, String mainClass) {
        G.v().reset();
        Options.v().set_whole_program(true);
        Options.v().setPhaseOption("cg.spark", "on"); // Enable Spark for pointer analysis
        Options.v().set_output_format(Options.output_format_none);
        Options.v().set_no_bodies_for_excluded(true);
        Options.v().set_allow_phantom_refs(true);

        List<String> includeList = new LinkedList<>();
        includeList.add("java.lang.*");
        includeList.add("java.util.*");
        includeList.add("java.io.*");

        Options.v().set_include(includeList);
        Options.v().set_soot_classpath(sootClassPath);
        Options.v().set_prepend_classpath(true);

        Scene.v().loadNecessaryClasses();
        SootClass c = Scene.v().forceResolve(mainClass, SootClass.BODIES);
        if (c != null) {
            c.setApplicationClass();
        }

    }

}