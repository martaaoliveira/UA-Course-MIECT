package lab12.reflection;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Plugin {

    public static void main(String[] args) throws Exception {
        File proxyList = new File("src/lab12/reflection");
        if (!proxyList.exists() || !proxyList.isDirectory()) {
            System.out.println("Invalid directory: lab12/reflection");
            return;
        }

        ArrayList<IPlugin> plgs = new ArrayList<IPlugin>();
        for (String f : proxyList.list()) {
            if (f.endsWith(".class")) {
                try {
                    IPlugin plugin = PluginManager.load("lab12.reflection." + f.substring(0, f.lastIndexOf('.')));
                    plgs.add(plugin);
                } catch (Exception e) {
                    System.out.println("\t" + f + ": Componente ignorado. Não é IPlugin.");
                }
            }
        }

        Iterator<IPlugin> it = plgs.iterator();
        while (it.hasNext()) {
            IPlugin plugin = it.next();
            System.out.println("Visiting plugin: " + plugin.getClass().getSimpleName());
            plugin.fazQualquerCoisa();
        }
    }
}
