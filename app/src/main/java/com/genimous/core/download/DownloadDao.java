package com.genimous.core.download;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "download".
*/
public class DownloadDao extends AbstractDao<DownloadDBEntity, String> {

    public static final String TABLENAME = "download";

    /**
     * Properties of entity DownloadDBEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property DownloadId = new Property(0, String.class, "downloadId", true, "DOWNLOAD_ID");
        public final static Property ToolSize = new Property(1, Long.class, "toolSize", false, "TOOL_SIZE");
        public final static Property CompletedSize = new Property(2, Long.class, "completedSize", false, "COMPLETED_SIZE");
        public final static Property Url = new Property(3, String.class, "url", false, "URL");
        public final static Property SaveDirPath = new Property(4, String.class, "saveDirPath", false, "SAVE_DIR_PATH");
        public final static Property FileName = new Property(5, String.class, "fileName", false, "FILE_NAME");
        public final static Property DownloadStatus = new Property(6, Integer.class, "downloadStatus", false, "DOWNLOAD_STATUS");
    };


    public DownloadDao(DaoConfig config) {
        super(config);
    }
    
    public DownloadDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"download\" (" + //
                "\"DOWNLOAD_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: downloadId
                "\"TOOL_SIZE\" INTEGER," + // 1: toolSize
                "\"COMPLETED_SIZE\" INTEGER," + // 2: completedSize
                "\"URL\" TEXT," + // 3: url
                "\"SAVE_DIR_PATH\" TEXT," + // 4: saveDirPath
                "\"FILE_NAME\" TEXT," + // 5: fileName
                "\"DOWNLOAD_STATUS\" INTEGER);"); // 6: downloadStatus
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"download\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DownloadDBEntity entity) {
        stmt.clearBindings();
 
        String downloadId = entity.getDownloadId();
        if (downloadId != null) {
            stmt.bindString(1, downloadId);
        }
 
        Long toolSize = entity.getToolSize();
        if (toolSize != null) {
            stmt.bindLong(2, toolSize);
        }
 
        Long completedSize = entity.getCompletedSize();
        if (completedSize != null) {
            stmt.bindLong(3, completedSize);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(4, url);
        }
 
        String saveDirPath = entity.getSaveDirPath();
        if (saveDirPath != null) {
            stmt.bindString(5, saveDirPath);
        }
 
        String fileName = entity.getFileName();
        if (fileName != null) {
            stmt.bindString(6, fileName);
        }
 
        Integer downloadStatus = entity.getDownloadStatus();
        if (downloadStatus != null) {
            stmt.bindLong(7, downloadStatus);
        }
    }

    /** @inheritdoc */
    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DownloadDBEntity readEntity(Cursor cursor, int offset) {
        DownloadDBEntity entity = new DownloadDBEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // downloadId
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // toolSize
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // completedSize
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // url
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // saveDirPath
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // fileName
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6) // downloadStatus
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DownloadDBEntity entity, int offset) {
        entity.setDownloadId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setToolSize(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setCompletedSize(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setUrl(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSaveDirPath(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFileName(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDownloadStatus(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected String updateKeyAfterInsert(DownloadDBEntity entity, long rowId) {
        return entity.getDownloadId();
    }
    
    /** @inheritdoc */
    @Override
    public String getKey(DownloadDBEntity entity) {
        if(entity != null) {
            return entity.getDownloadId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
