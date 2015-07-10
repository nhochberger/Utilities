package hochberger.utilities.application;

import java.util.Comparator;

public class VersionComparator implements Comparator<String> {

    public VersionComparator() {
        super();
    }

    @Override
    public int compare(final String oldVersion, final String newVersion) {
        System.err.println("---------");
        final String[] oldDigits = oldVersion.split("\\.");
        final String[] newDigits = newVersion.split("\\.");
        for (int i = 0; i < Math.min(oldDigits.length, newDigits.length); i++) {
            if (!(oldDigits[i].equals(newDigits[i]))) {
                return Integer.valueOf(oldDigits[i]).intValue()
                        - Integer.valueOf(newDigits[i]).intValue();
            }
        }
        return 0;
    }
}
