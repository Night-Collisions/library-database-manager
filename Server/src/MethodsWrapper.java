public class MethodsWrapper {

    /* Get user info on authorization */

    public static String authUser(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.authUser(data[2], data[3]);
    }

    /* Get user's publications ids on authorization */

    public static String getUserPubl(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getUserPubl(data[0]);
    }

    /* Get tables */

    public static String getUsers(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getUsers(data[0]);
    }

    public static String getPublications(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getPublications();
    }

    public static String getBooks(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getBooks();
    }

    public static String getDigests(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getDigests();
    }

    public static String getArticles(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getArticles();
    }

    public static String getTheses(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getTheses();
    }

    public static String getDocs(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getDocs();
    }

    // TODO getSubjects

    public static String getMagazines(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getMagazines();
    }

    public static String getAuthors(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getAuthors();
    }

    public static String getEditors(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getEditors();
    }

    public static String getOrganizations(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getOrganizations();
    }

    public static String getPublHouses(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getPublHouses();
    }

    public static String getAllKeywords(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getAllKeywords();
    }

    public static String getAllUdc(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getAllUdc();
    }

    public static String getVerfs(String[] data) {
        if (data.length != 2) {
            return "wrong args";
        }
        return Server.db.getVerfs(data[0]);
    }

    public static String getAuthOrg(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.getAuthOrg(data[2]);
    }

    /* Get rows that references to publication */

    public static String getAuthorsOfPubl(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.getAuthorsOfPubl(data[2]);
    }

    public static String getEditorsOfPubl(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.getEditorsOfPubl(data[2]);
    }

    public static String getKeywordsOfPubl(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.getKeywordsOfPubl(data[2]);
    }

    public static String getUdcOfPubl(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.getUdcOfPubl(data[2]);
    }

    /* Add rows to tables */

    public static String addUser(String[] data) {
        if (data.length != 12) {
            return "wrong args";
        }
        return Server.db.addUser(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                data[9], data[10], data[11]);
    }

    public static String addBook(String[] data) {
        if (data.length != 5) {
            return "wrong args";
        }
        return Server.db.addBook(data[0], data[2], data[3], data[4]);
    }

    public static String addDigest(String[] data) {
        if (data.length != 5) {
            return "wrong args";
        }
        return Server.db.addDigest(data[0], data[2], data[3], data[4]);
    }

    public static String addMArticle(String[] data) {
        if (data.length != 6) {
            return "wrong args";
        }
        return Server.db.addMArticle(data[0], data[2], data[3], data[4], data[5]);
    }

    public static String addDArticle(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.addDArticle(data[0], data[2], data[3]);
    }

    public static String addMTheses(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.addMTheses(data[0], data[2], data[3]);
    }

    public static String addDTheses(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.addDTheses(data[0], data[2], data[3]);
    }

    public static String addDocs(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.addDocs(data[0], data[2], data[3]);
    }

    public static String addSubject(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.addSubject(data[0], data[2]);
    }

    public static String addMagazine(String[] data) {
        if (data.length != 5) {
            return "wrong args";
        }
        return Server.db.addMagazine(data[0], data[2], data[3], data[4]);
    }

    public static String addAuthor(String[] data) {
        if (data.length != 10) {
            return "wrong args";
        }
        return Server.db.addAuthor(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                data[9]);
    }

    public static String addEditor(String[] data) {
        if (data.length != 10) {
            return "wrong args";
        }
        return Server.db.addEditor(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                data[9]);
    }

    public static String addOrganization(String[] data) {
        if (data.length != 6) {
            return "wrong args";
        }
        return Server.db.addOrganization(data[0], data[2], data[3], data[4], data[5]);
    }

    public static String addPublHouse(String[] data) {
        if (data.length != 6) {
            return "wrong args";
        }
        return Server.db.addPublHouse(data[0], data[2], data[3], data[4], data[5]);
    }

    public static String addKeyword(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.addKeyword(data[0], data[2]);
    }

    public static String addUdc(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.addUdc(data[0], data[2]);
    }

    public static String addVerf(String[] data) {
        if (data.length != 5) {
            return "wrong args";
        }
        return Server.db.addVerf(data[0], data[2], data[3], data[4]);
    }

    public static String addAuthOrg(String[] data) {
        if (data.length != 6) {
            return "wrong args";
        }
        return Server.db.addAuthOrg(data[0], data[2], data[3], data[4], data[5]);
    }

    /* Add references to publication */

    public static String addAuthToPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.addAuthToPubl(data[0], data[2], data[3]);
    }

    public static String addEditorToPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.addEditorToPubl(data[0], data[2], data[3]);
    }

    public static String addKeywordToPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.addKeywordToPubl(data[0], data[2], data[3]);
    }

    public static String addUdcToPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.addUdcToPubl(data[0], data[2], data[3]);
    }

    /* Accept verification */

    public static String addUserToAuthor(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.addUserToAuthor(data[0], data[2], data[3]);
    }

    public static String addUserToPublHouse(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.addUserToPublHouse(data[0], data[2], data[3]);
    }

    /* Delete from tables */

    public static String deleteUser(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.deleteUser(data[0], data[2]);
    }

    public static String deletePublication(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.deletePublication(data[0], data[2]);
    }

    public static String deleteSubject(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.deleteSubject(data[0], data[2]);
    }

    public static String deleteMagazine(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.deleteMagazine(data[0], data[2]);
    }

    public static String deleteAuthor(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.deleteAuthor(data[0], data[2]);
    }

    public static String deleteEditor(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.deleteEditor(data[0], data[2]);
    }

    public static String deleteOrganization(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.deleteOrganization(data[0], data[2]);
    }

    public static String deletePublHouse(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.deletePublHouse(data[0], data[2]);
    }

    public static String deleteKeyword(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.deleteKeyword(data[0], data[2]);
    }

    public static String deleteUdc(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.deleteUdc(data[0], data[2]);
    }

    public static String deleteVerification(String[] data) {
        if (data.length != 3) {
            return "wrong args";
        }
        return Server.db.deleteVerification(data[0], data[2]);
    }

    // TODO deleteAuthOrg

    /* Remove reference from publication */

    public static String deleteAuthFromPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.deleteAuthFromPubl(data[0], data[2], data[3]);
    }

    public static String deleteEditorFromPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.deleteEditorFromPubl(data[0], data[2], data[3]);
    }

    public static String deleteKeywordFromPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.deleteKeywordFromPubl(data[0], data[2], data[3]);
    }

    public static String deleteUdcFromPubl(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.deleteUdcFromPubl(data[0], data[2], data[3]);
    }

    /* Changing */

    public static String changeUser(String[] data) {
        if (data.length != 10) {
            return "wrong args";
        }
        return Server.db.changeUser(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                data[9]);
    }

    public static String changeUserPassword(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.changeUserPassword(data[0], data[2], data[3]);
    }

    public static String changeBook(String[] data) {
        if (data.length != 5) {
            return "wrong args";
        }
        return Server.db.changeBook(data[0], data[2], data[3], data[4]);
    }

    public static String changeDigest(String[] data) {
        if (data.length != 5) {
            return "wrong args";
        }
        return Server.db.changeDigest(data[0], data[2], data[3], data[4]);
    }

    public static String changeArticle(String[] data) {
        if (data.length != 6) {
            return "wrong args";
        }
        return Server.db.changeArticle(data[0], data[2], data[3], data[4], data[5]);
    }

    public static String changeTheses(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.changeTheses(data[0], data[2], data[3]);
    }

    public static String changeDocs(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.changeDocs(data[0], data[2], data[3]);
    }

    public static String changeSubject(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.changeSubject(data[0], data[2], data[3]);
    }

    public static String changeMagazine(String[] data) {
        if (data.length != 4) {
            return "wrong args";
        }
        return Server.db.changeMagazine(data[0], data[2], data[3]);
    }

    public static String changeOrganization(String[] data) {
        if (data.length != 7) {
            return "wrong args";
        }
        return Server.db.changeOrganization(data[0], data[2], data[3], data[4], data[5], data[6]);
    }

    public static String changePublHouse(String[] data) {
        if (data.length != 7) {
            return "wrong args";
        }
        return Server.db.changePublHouse(data[0], data[2], data[3], data[4], data[5], data[6]);
    }

    public static String changeAuthor(String[] data) {
        if (data.length != 11) {
            return "wrong args";
        }
        return Server.db.changeAuthor(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                data[9], data[10]);
    }

    public static String changeEditor(String[] data) {
        if (data.length != 11) {
            return "wrong args";
        }
        return Server.db.changeEditor(data[0], data[2], data[3], data[4], data[5], data[6], data[7], data[8],
                data[9], data[10]);
    }
}
