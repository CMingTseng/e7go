package tw.haotek.dut.data;

import com.tutk.IOTC.AVIOCTRLDEFs;

/**
 * Created by Neo on 2016/1/18 0018.
 */
public class PhotoInfo {
    public static final int EVENT_UNREADED = 0;
    public static final int EVENT_READED = 1;
    public static final int EVENT_NORECORD = 2;
    public int mEventType;
    public AVIOCTRLDEFs.STimeDay mEventTime;//FIXME
    public int mEventStatus;
    public String mPath;
    public boolean mHasFile = false;
    public boolean mIsSelect = false;

    public PhotoInfo(int eventtype, AVIOCTRLDEFs.STimeDay eventtime, int eventstatus, String path) {
        mEventType = eventtype;
        mEventTime = eventtime;
        mEventStatus = eventstatus;
        mPath = path;
    }

    public PhotoInfo(int eventtype, AVIOCTRLDEFs.STimeDay eventtime, int eventstatus, String path, boolean hasFile) {
        mEventType = eventtype;
        mEventTime = eventtime;
        mEventStatus = eventstatus;
        mPath = path;
        mHasFile = hasFile;
    }

    public void setHasFile(boolean hasFile) {
        mHasFile = hasFile;
    }

    public void selectItem(boolean select) {
        mIsSelect = select;
    }
}
