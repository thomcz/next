package com.org.thomcz.next.util;

import android.content.Context;

import java.io.FileOutputStream;

/**
 * Created by Thomas on 29.07.2015.
 */
public class SaveGameParser {
    private static final String ns = null;
    private static final String FEED_TAG = "feed";
    private static final String LEVEL_ENTRY_TAG = "level";
    private static final String STAGE_ENTRY_TAG = "stage";
    private static final String UNLOCKED_TAG = "unlocked";
    private static final String SCORE_TAG = "score";

    public void parse(Context c) {
        String filename = "myfile";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = c.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public void parse(InputStream in, ArrayList<Level> levels, ArrayList<Stage> stages) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            readFeed(parser, levels, stages);
        } finally {
            in.close();
        }
    }

    private void readFeed(XmlPullParser parser, ArrayList<Level> levels, ArrayList<Stage> stages) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, FEED_TAG);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String name = parser.getName();
            // Starts by looking for the entry tag
            if (name.equals(LEVEL_ENTRY_TAG)) {
                readLevelEntry(parser, levels);
            } else if (name.equals(STAGE_ENTRY_TAG)) {
                readStageEntry(parser, stages);
            } else {
                skip(parser);
            }
        }
    }

    // Parses the contents of an entry. If it encounters a title, summary, or link tag, hands them off
// to their respective "read" methods for processing. Otherwise, skips the tag.
    private void readLevelEntry(XmlPullParser parser, ArrayList<Level> levels) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, LEVEL_ENTRY_TAG);
        boolean levelUnlocked = false;
        //boolean stageUnlocked = false;
        int score = 0;
        int extraScore = 0;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            int index = 0;
            String name = parser.getName();
            if (name.equals(UNLOCKED_TAG)) {
                levelUnlocked = readLevelUnlocked(parser);
            } else if (name.equals(SCORE_TAG)) {
                score = readScore(parser);
            } else {
                skip(parser);
            }
            levels.get(index).setUnlocked(levelUnlocked);
            levels.get(index).setScore(score);
            levels.get(index).setExtraScore(extraScore);
            index++;
        }
    }

    // Processes title tags in the feed.
    private boolean readLevelUnlocked(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, UNLOCKED_TAG);
        boolean unlocked = false;
        unlocked = Boolean.getBoolean(readText(parser));
        parser.require(XmlPullParser.END_TAG, ns, UNLOCKED_TAG);
        return unlocked;
    }

    // Processes summary tags in the feed.
    private int readScore(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, SCORE_TAG);
        int score = 0;
        try {
            score = Integer.getInteger(readText(parser));
        } catch (NumberFormatException e) {

        }
        parser.require(XmlPullParser.END_TAG, ns, SCORE_TAG);
        return score;
    }

    // For the tags title and summary, extracts their text values.
    private String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    private void readStageEntry(XmlPullParser parser, ArrayList<Stage> stages) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, ns, STAGE_ENTRY_TAG);
       boolean stageUnlocked = false;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            int index = 0;
            String name = parser.getName();
            if (name.equals(UNLOCKED_TAG)) {
                stageUnlocked = readStageUnlocked(parser);
            } else {
                skip(parser);
            }
            stages.get(index).setUnlocked(stageUnlocked);
            index++;
        }
    }

    // Processes title tags in the feed.
    private boolean readStageUnlocked(XmlPullParser parser) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, UNLOCKED_TAG);
        boolean unlocked = false;
        unlocked = Boolean.getBoolean(readText(parser));
        parser.require(XmlPullParser.END_TAG, ns, UNLOCKED_TAG);
        return unlocked;
    }

    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }*/
}
