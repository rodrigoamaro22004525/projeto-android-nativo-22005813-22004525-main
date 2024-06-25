package pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FilmeDatabase_Impl extends FilmeDatabase {
  private volatile FilmeDao _filmeDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `filmes` (`idFilme` TEXT NOT NULL, `idCinema` INTEGER NOT NULL, `linkInfo` TEXT NOT NULL, `avaliacaoUtilizador` INTEGER NOT NULL, `data` INTEGER NOT NULL, `observacoes` TEXT NOT NULL, PRIMARY KEY(`idFilme`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `infos` (`linkInfo` TEXT NOT NULL, `name` TEXT NOT NULL, `imagemCartaz` TEXT NOT NULL, `sinopse` TEXT NOT NULL, `dataLancamento` INTEGER NOT NULL, `avaliacaoIMDB` REAL NOT NULL, PRIMARY KEY(`linkInfo`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `photos` (`photoStr` TEXT NOT NULL, `idFilme` TEXT NOT NULL, PRIMARY KEY(`photoStr`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `genres` (`nameGenre` TEXT NOT NULL, PRIMARY KEY(`nameGenre`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `InfoGenreCrossRef` (`linkInfo` TEXT NOT NULL, `nameGenre` TEXT NOT NULL, PRIMARY KEY(`linkInfo`, `nameGenre`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'f00ce4718ee1720e18a5af7c9cd06750')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `filmes`");
        _db.execSQL("DROP TABLE IF EXISTS `infos`");
        _db.execSQL("DROP TABLE IF EXISTS `photos`");
        _db.execSQL("DROP TABLE IF EXISTS `genres`");
        _db.execSQL("DROP TABLE IF EXISTS `InfoGenreCrossRef`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      public void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsFilmes = new HashMap<String, TableInfo.Column>(6);
        _columnsFilmes.put("idFilme", new TableInfo.Column("idFilme", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFilmes.put("idCinema", new TableInfo.Column("idCinema", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFilmes.put("linkInfo", new TableInfo.Column("linkInfo", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFilmes.put("avaliacaoUtilizador", new TableInfo.Column("avaliacaoUtilizador", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFilmes.put("data", new TableInfo.Column("data", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFilmes.put("observacoes", new TableInfo.Column("observacoes", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFilmes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFilmes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFilmes = new TableInfo("filmes", _columnsFilmes, _foreignKeysFilmes, _indicesFilmes);
        final TableInfo _existingFilmes = TableInfo.read(_db, "filmes");
        if (! _infoFilmes.equals(_existingFilmes)) {
          return new RoomOpenHelper.ValidationResult(false, "filmes(pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.FilmeDB).\n"
                  + " Expected:\n" + _infoFilmes + "\n"
                  + " Found:\n" + _existingFilmes);
        }
        final HashMap<String, TableInfo.Column> _columnsInfos = new HashMap<String, TableInfo.Column>(6);
        _columnsInfos.put("linkInfo", new TableInfo.Column("linkInfo", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInfos.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInfos.put("imagemCartaz", new TableInfo.Column("imagemCartaz", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInfos.put("sinopse", new TableInfo.Column("sinopse", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInfos.put("dataLancamento", new TableInfo.Column("dataLancamento", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInfos.put("avaliacaoIMDB", new TableInfo.Column("avaliacaoIMDB", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInfos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInfos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInfos = new TableInfo("infos", _columnsInfos, _foreignKeysInfos, _indicesInfos);
        final TableInfo _existingInfos = TableInfo.read(_db, "infos");
        if (! _infoInfos.equals(_existingInfos)) {
          return new RoomOpenHelper.ValidationResult(false, "infos(pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.InfoDB).\n"
                  + " Expected:\n" + _infoInfos + "\n"
                  + " Found:\n" + _existingInfos);
        }
        final HashMap<String, TableInfo.Column> _columnsPhotos = new HashMap<String, TableInfo.Column>(2);
        _columnsPhotos.put("photoStr", new TableInfo.Column("photoStr", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPhotos.put("idFilme", new TableInfo.Column("idFilme", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPhotos = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPhotos = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPhotos = new TableInfo("photos", _columnsPhotos, _foreignKeysPhotos, _indicesPhotos);
        final TableInfo _existingPhotos = TableInfo.read(_db, "photos");
        if (! _infoPhotos.equals(_existingPhotos)) {
          return new RoomOpenHelper.ValidationResult(false, "photos(pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.PhotoDB).\n"
                  + " Expected:\n" + _infoPhotos + "\n"
                  + " Found:\n" + _existingPhotos);
        }
        final HashMap<String, TableInfo.Column> _columnsGenres = new HashMap<String, TableInfo.Column>(1);
        _columnsGenres.put("nameGenre", new TableInfo.Column("nameGenre", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGenres = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGenres = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGenres = new TableInfo("genres", _columnsGenres, _foreignKeysGenres, _indicesGenres);
        final TableInfo _existingGenres = TableInfo.read(_db, "genres");
        if (! _infoGenres.equals(_existingGenres)) {
          return new RoomOpenHelper.ValidationResult(false, "genres(pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.GenreDB).\n"
                  + " Expected:\n" + _infoGenres + "\n"
                  + " Found:\n" + _existingGenres);
        }
        final HashMap<String, TableInfo.Column> _columnsInfoGenreCrossRef = new HashMap<String, TableInfo.Column>(2);
        _columnsInfoGenreCrossRef.put("linkInfo", new TableInfo.Column("linkInfo", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInfoGenreCrossRef.put("nameGenre", new TableInfo.Column("nameGenre", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInfoGenreCrossRef = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInfoGenreCrossRef = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInfoGenreCrossRef = new TableInfo("InfoGenreCrossRef", _columnsInfoGenreCrossRef, _foreignKeysInfoGenreCrossRef, _indicesInfoGenreCrossRef);
        final TableInfo _existingInfoGenreCrossRef = TableInfo.read(_db, "InfoGenreCrossRef");
        if (! _infoInfoGenreCrossRef.equals(_existingInfoGenreCrossRef)) {
          return new RoomOpenHelper.ValidationResult(false, "InfoGenreCrossRef(pt.ulusofona.deisi.cm2223.g22005813_22004525.data.local.relations.InfoGenreCrossRef).\n"
                  + " Expected:\n" + _infoInfoGenreCrossRef + "\n"
                  + " Found:\n" + _existingInfoGenreCrossRef);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "f00ce4718ee1720e18a5af7c9cd06750", "53ecb5e24ecc6d6bf7002be7800788b1");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "filmes","infos","photos","genres","InfoGenreCrossRef");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `filmes`");
      _db.execSQL("DELETE FROM `infos`");
      _db.execSQL("DELETE FROM `photos`");
      _db.execSQL("DELETE FROM `genres`");
      _db.execSQL("DELETE FROM `InfoGenreCrossRef`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(FilmeDao.class, FilmeDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public FilmeDao getFilmeDao() {
    if (_filmeDao != null) {
      return _filmeDao;
    } else {
      synchronized(this) {
        if(_filmeDao == null) {
          _filmeDao = new FilmeDao_Impl(this);
        }
        return _filmeDao;
      }
    }
  }
}
