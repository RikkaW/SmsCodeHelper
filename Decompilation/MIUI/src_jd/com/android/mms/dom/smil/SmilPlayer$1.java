package com.android.mms.dom.smil;

import java.util.Comparator;

final class SmilPlayer$1
  implements Comparator<SmilPlayer.TimelineEntry>
{
  public int compare(SmilPlayer.TimelineEntry paramTimelineEntry1, SmilPlayer.TimelineEntry paramTimelineEntry2)
  {
    return Double.compare(paramTimelineEntry1.getOffsetTime(), paramTimelineEntry2.getOffsetTime());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.dom.smil.SmilPlayer.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */