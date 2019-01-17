class MethodsWrapper {

    /* Get user info on authorization */

    static String authorizeUser(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.authorizeUser(args[2], args[3]);
    }

    /* Get user's publications ids on authorization */

    static String getPublsOfUser(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getPublsOfUser(args[0]);
    }

    /* Get tables */

    static String getUsers(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getUsers(args[0]);
    }

    static String getPublications(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getPublications();
    }

    static String getBooks(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getBooks();
    }

    static String getDigests(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getDigests();
    }

    static String getArticles(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getArticles();
    }

    static String getTheses(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getTheses();
    }

    static String getDocs(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getDocs();
    }

    static String getSubjects(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getSubjects();
    }

    static String getMagazines(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getMagazines();
    }

    static String getAuthors(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getAuthors();
    }

    static String getEditors(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getEditors();
    }

    static String getOrganizations(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getOrganizations();
    }

    static String getPublHouses(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getPublHouses();
    }

    static String getKeywords(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getKeywords();
    }

    static String getUdc(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getUdc();
    }

    static String getVerfs(String[] args) {
        if (args.length != 2) {
            return "wrong args";
        }
        return Main.db.getVerfs(args[0]);
    }

    static String getAuthorsWorktimes(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.getAuthorsWorktimes(args[2]);
    }

    /* Get rows that refer to publication */

    static String getAuthorsOfPubl(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.getAuthorsOfPubl(args[2]);
    }

    static String getEditorsOfPubl(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.getEditorsOfPubl(args[2]);
    }

    static String getKeywordsOfPubl(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.getKeywordsOfPubl(args[2]);
    }

    static String getUdcOfPubl(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.getUdcOfPubl(args[2]);
    }

    /* Get rows that don't refer to publication */

    static String getAuthorsNotOfPubl(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.getAuthorsNotOfPubl(args[2]);
    }

    static String getEditorsNotOfPubl(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.getEditorsNotOfPubl(args[2]);
    }

    static String getKeywordsNotOfPubl(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.getKeywordsNotOfPubl(args[2]);
    }

    static String getUdcNotOfPubl(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.getUdcNotOfPubl(args[2]);
    }

    /* Add rows to tables */

    static String addUser(String[] args) {
        if (args.length != 12) {
            return "wrong args";
        }
        return Main.db.addUser(args[0], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
                args[9], args[10], args[11]);
    }

    static String addBook(String[] args) {
        if (args.length != 5) {
            return "wrong args";
        }
        return Main.db.addBook(args[0], args[2], args[3], args[4]);
    }

    static String addDigest(String[] args) {
        if (args.length != 5) {
            return "wrong args";
        }
        return Main.db.addDigest(args[0], args[2], args[3], args[4]);
    }

    static String addMagazineArticle(String[] args) {
        if (args.length != 6) {
            return "wrong args";
        }
        return Main.db.addMagazineArticle(args[0], args[2], args[3], args[4], args[5]);
    }

    static String addDigestArticle(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.addDigestArticle(args[0], args[2], args[3]);
    }

    static String addMagazineTheses(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.addMagazineTheses(args[0], args[2], args[3]);
    }

    static String addDigestTheses(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.addDigestTheses(args[0], args[2], args[3]);
    }

    static String addDocs(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.addDocs(args[0], args[2], args[3]);
    }

    static String addSubject(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.addSubject(args[0], args[2]);
    }

    static String addMagazine(String[] args) {
        if (args.length != 5) {
            return "wrong args";
        }
        return Main.db.addMagazine(args[0], args[2], args[3], args[4]);
    }

    static String addAuthor(String[] args) {
        if (args.length != 10) {
            return "wrong args";
        }
        return Main.db.addAuthor(args[0], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
                args[9]);
    }

    static String addEditor(String[] args) {
        if (args.length != 10) {
            return "wrong args";
        }
        return Main.db.addEditor(args[0], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
                args[9]);
    }

    static String addOrganization(String[] args) {
        if (args.length != 6) {
            return "wrong args";
        }
        return Main.db.addOrganization(args[0], args[2], args[3], args[4], args[5]);
    }

    static String addPublHouse(String[] args) {
        if (args.length != 6) {
            return "wrong args";
        }
        return Main.db.addPublHouse(args[0], args[2], args[3], args[4], args[5]);
    }

    static String addKeyword(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.addKeyword(args[0], args[2]);
    }

    static String addUdc(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.addUdc(args[0], args[2]);
    }

    static String addVerf(String[] args) {
        if (args.length != 5) {
            return "wrong args";
        }
        return Main.db.addVerf(args[0], args[2], args[3], args[4]);
    }

    static String addAuthorWorktime(String[] args) {
        if (args.length != 6) {
            return "wrong args";
        }
        return Main.db.addAuthorWorktime(args[0], args[2], args[3], args[4], args[5]);
    }

    /* Add references to publication */

    static String addAuthorToPubl(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.addAuthorToPubl(args[0], args[2], args[3]);
    }

    static String addEditorToPubl(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.addEditorToPubl(args[0], args[2], args[3]);
    }

    static String addKeywordToPubl(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.addKeywordToPubl(args[0], args[2], args[3]);
    }

    static String addUdcToPubl(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.addUdcToPubl(args[0], args[2], args[3]);
    }

    /* Accept verification */

    static String addUserToAuthor(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.addUserToAuthor(args[0], args[2], args[3]);
    }

    static String addUserToPublHouse(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.addUserToPublHouse(args[0], args[2], args[3]);
    }

    /* Delete from tables */

    static String deleteUser(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteUser(args[0], args[2]);
    }

    static String deletePublication(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.deletePublication(args[0], args[2]);
    }

    static String deleteSubject(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteSubject(args[0], args[2]);
    }

    static String deleteMagazine(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteMagazine(args[0], args[2]);
    }

    static String deleteAuthor(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteAuthor(args[0], args[2]);
    }

    static String deleteEditor(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteEditor(args[0], args[2]);
    }

    static String deleteOrganization(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteOrganization(args[0], args[2]);
    }

    static String deletePublHouse(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.deletePublHouse(args[0], args[2]);
    }

    static String deleteKeyword(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteKeyword(args[0], args[2]);
    }

    static String deleteUdc(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteUdc(args[0], args[2]);
    }

    static String deleteVerf(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteVerf(args[0], args[2]);
    }

    static String deleteAuthorWorktime(String[] args) {
        if (args.length != 3) {
            return "wrong args";
        }
        return Main.db.deleteAuthorWorktime(args[0], args[2]);
    }

    /* Remove reference from publication */

    static String deleteAuthorFromPubl(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.deleteAuthorFromPubl(args[0], args[2], args[3]);
    }

    static String deleteEditorFromPubl(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.deleteEditorFromPubl(args[0], args[2], args[3]);
    }

    static String deleteKeywordFromPubl(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.deleteKeywordFromPubl(args[0], args[2], args[3]);
    }

    static String deleteUdcFromPubl(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.deleteUdcFromPubl(args[0], args[2], args[3]);
    }

    /* Changing */

    static String changeUserInfo(String[] args) {
        if (args.length != 10) {
            return "wrong args";
        }
        return Main.db.changeUserInfo(args[0], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
                args[9]);
    }

    static String changeUserPassword(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.changeUserPassword(args[0], args[2], args[3]);
    }

    static String changeBook(String[] args) {
        if (args.length != 5) {
            return "wrong args";
        }
        return Main.db.changeBook(args[0], args[2], args[3], args[4]);
    }

    static String changeDigest(String[] args) {
        if (args.length != 5) {
            return "wrong args";
        }
        return Main.db.changeDigest(args[0], args[2], args[3], args[4]);
    }

    static String changeArticle(String[] args) {
        if (args.length != 6) {
            return "wrong args";
        }
        return Main.db.changeArticle(args[0], args[2], args[3], args[4], args[5]);
    }

    static String changeTheses(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.changeTheses(args[0], args[2], args[3]);
    }

    static String changeDocs(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.changeDocs(args[0], args[2], args[3]);
    }

    static String changeSubject(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.changeSubject(args[0], args[2], args[3]);
    }

    static String changeMagazine(String[] args) {
        if (args.length != 4) {
            return "wrong args";
        }
        return Main.db.changeMagazine(args[0], args[2], args[3]);
    }

    static String changeOrganization(String[] args) {
        if (args.length != 7) {
            return "wrong args";
        }
        return Main.db.changeOrganization(args[0], args[2], args[3], args[4], args[5], args[6]);
    }

    static String changePublHouse(String[] args) {
        if (args.length != 7) {
            return "wrong args";
        }
        return Main.db.changePublHouse(args[0], args[2], args[3], args[4], args[5], args[6]);
    }

    static String changeAuthor(String[] args) {
        if (args.length != 11) {
            return "wrong args";
        }
        return Main.db.changeAuthor(args[0], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
                args[9], args[10]);
    }

    static String changeEditor(String[] args) {
        if (args.length != 11) {
            return "wrong args";
        }
        return Main.db.changeEditor(args[0], args[2], args[3], args[4], args[5], args[6], args[7], args[8],
                args[9], args[10]);
    }
}
