package com.data.big.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
@Table(name = "new_wind_speed_alarm")
public class NewWindSpeedAlarm implements Serializable {
    /**
     * 
     */
    @Id
    private String id;

    /**
     * 
     */
    private String actualsnowdepth;

    /**
     * 
     */
    private String alarmbasic;

    /**
     * 
     */
    private String alarmconfirmtime;

    /**
     * 
     */
    private String alarmflag;

    /**
     * 
     */
    private String alarmlevel;

    /**
     * 
     */
    private String alarmresumeconfirmtime;

    /**
     * 
     */
    private String alarmresumetime;

    /**
     * 
     */
    private String alarmstatus;

    /**
     * 
     */
    private String alarmvalue;

    /**
     * 
     */
    private String area;

    /**
     * 
     */
    private String benchmarksnowdepth;

    /**
     * 
     */
    private String bureaucode;

    /**
     * 
     */
    private String bureauname;

    /**
     * 
     */
    private String continuousrainfall;

    /**
     * 
     */
    private String cumulativerainfall;

    /**
     * 
     */
    private String dayrainfall;

    /**
     * 
     */
    private String dbcreatetime;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private String devicetype;

    /**
     * 
     */
    private String downtrackcontrolarea;

    /**
     * 
     */
    private String downtrackcontrolnum;

    /**
     * 
     */
    private String dtype;

    /**
     * 
     */
    private String dvalue;

    /**
     * 
     */
    private String dyss;

    /**
     * 
     */
    private String endtime;

    /**
     * 
     */
    private String handleaction;

    /**
     * 
     */
    private String hourrainfall;

    /**
     * 
     */
    private String km;

    /**
     * 
     */
    private String limitsegment;

    /**
     * 
     */
    private String limitspeed;

    /**
     * 
     */
    private String linecode;

    /**
     * 
     */
    private String linename;

    /**
     * 
     */
    private String monitorpoint;

    /**
     * 
     */
    private String monitortime;

    /**
     * 
     */
    private String monitorunit;

    /**
     * 
     */
    private String monitoringtime;

    /**
     * 
     */
    private String prosegmenttype;

    /**
     * 
     */
    private String rainfallintensity;

    /**
     * 
     */
    private String segment;

    /**
     * 
     */
    private String segmenttype;

    /**
     * 
     */
    private String sensorcode;

    /**
     * 
     */
    private String snowdepth;

    /**
     * 
     */
    private String starttime;

    /**
     * 
     */
    private String tenminuterainfall;

    /**
     * 
     */
    private String tweentyfourhourrainfall;

    /**
     * 
     */
    private String uptrackcontrolarea;

    /**
     * 
     */
    private String uptrackcontrolnum;

    /**
     * 
     */
    private String winddirection;

    /**
     * 
     */
    private String winddirection1;

    /**
     * 
     */
    private String winddirection2;

    /**
     * 
     */
    private String windspeed;

    /**
     * 
     */
    private String windspeed1;

    /**
     * 
     */
    private String windspeed2;

    /**
     * 
     */
    private String xyes;

    /**
     * 
     */
    private String xyesw;

    /**
     * 
     */
    private String xys;

    /**
     * 
     */
    private String xyss;

    /**
     * 
     */
    private String xysw;

    /**
     * 
     */
    private String xyw;

    /**
     * 0 为生成任务 1 以生成任务
     */
    private String videostatus;
    /**
     * 类型
     */
    @Transient
    private String alarmType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table new_wind_speed_alarm
     *
     * @mbg.generated Wed Oct 28 09:09:05 CST 2020
     */
    private static final long serialVersionUID = 1L;

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getActualsnowdepth() {
        return actualsnowdepth;
    }

    public void setActualsnowdepth(String actualsnowdepth) {
        this.actualsnowdepth = actualsnowdepth == null ? null : actualsnowdepth.trim();
    }

    public String getAlarmbasic() {
        return alarmbasic;
    }

    public void setAlarmbasic(String alarmbasic) {
        this.alarmbasic = alarmbasic == null ? null : alarmbasic.trim();
    }

    public String getAlarmconfirmtime() {
        return alarmconfirmtime;
    }

    public void setAlarmconfirmtime(String alarmconfirmtime) {
        this.alarmconfirmtime = alarmconfirmtime == null ? null : alarmconfirmtime.trim();
    }

    public String getAlarmflag() {
        return alarmflag;
    }

    public void setAlarmflag(String alarmflag) {
        this.alarmflag = alarmflag == null ? null : alarmflag.trim();
    }

    public String getAlarmlevel() {
        return alarmlevel;
    }

    public void setAlarmlevel(String alarmlevel) {
        this.alarmlevel = alarmlevel == null ? null : alarmlevel.trim();
    }

    public String getAlarmresumeconfirmtime() {
        return alarmresumeconfirmtime;
    }

    public void setAlarmresumeconfirmtime(String alarmresumeconfirmtime) {
        this.alarmresumeconfirmtime = alarmresumeconfirmtime == null ? null : alarmresumeconfirmtime.trim();
    }

    public String getAlarmresumetime() {
        return alarmresumetime;
    }

    public void setAlarmresumetime(String alarmresumetime) {
        this.alarmresumetime = alarmresumetime == null ? null : alarmresumetime.trim();
    }

    public String getAlarmstatus() {
        return alarmstatus;
    }

    public void setAlarmstatus(String alarmstatus) {
        this.alarmstatus = alarmstatus == null ? null : alarmstatus.trim();
    }

    public String getAlarmvalue() {
        return alarmvalue;
    }

    public void setAlarmvalue(String alarmvalue) {
        this.alarmvalue = alarmvalue == null ? null : alarmvalue.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getBenchmarksnowdepth() {
        return benchmarksnowdepth;
    }

    public void setBenchmarksnowdepth(String benchmarksnowdepth) {
        this.benchmarksnowdepth = benchmarksnowdepth == null ? null : benchmarksnowdepth.trim();
    }

    public String getBureaucode() {
        return bureaucode;
    }

    public void setBureaucode(String bureaucode) {
        this.bureaucode = bureaucode == null ? null : bureaucode.trim();
    }

    public String getBureauname() {
        return bureauname;
    }

    public void setBureauname(String bureauname) {
        this.bureauname = bureauname == null ? null : bureauname.trim();
    }

    public String getContinuousrainfall() {
        return continuousrainfall;
    }

    public void setContinuousrainfall(String continuousrainfall) {
        this.continuousrainfall = continuousrainfall == null ? null : continuousrainfall.trim();
    }

    public String getCumulativerainfall() {
        return cumulativerainfall;
    }

    public void setCumulativerainfall(String cumulativerainfall) {
        this.cumulativerainfall = cumulativerainfall == null ? null : cumulativerainfall.trim();
    }

    public String getDayrainfall() {
        return dayrainfall;
    }

    public void setDayrainfall(String dayrainfall) {
        this.dayrainfall = dayrainfall == null ? null : dayrainfall.trim();
    }

    public String getDbcreatetime() {
        return dbcreatetime;
    }

    public void setDbcreatetime(String dbcreatetime) {
        this.dbcreatetime = dbcreatetime == null ? null : dbcreatetime.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype == null ? null : devicetype.trim();
    }

    public String getDowntrackcontrolarea() {
        return downtrackcontrolarea;
    }

    public void setDowntrackcontrolarea(String downtrackcontrolarea) {
        this.downtrackcontrolarea = downtrackcontrolarea == null ? null : downtrackcontrolarea.trim();
    }

    public String getDowntrackcontrolnum() {
        return downtrackcontrolnum;
    }

    public void setDowntrackcontrolnum(String downtrackcontrolnum) {
        this.downtrackcontrolnum = downtrackcontrolnum == null ? null : downtrackcontrolnum.trim();
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype == null ? null : dtype.trim();
    }

    public String getDvalue() {
        return dvalue;
    }

    public void setDvalue(String dvalue) {
        this.dvalue = dvalue == null ? null : dvalue.trim();
    }

    public String getDyss() {
        return dyss;
    }

    public void setDyss(String dyss) {
        this.dyss = dyss == null ? null : dyss.trim();
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime == null ? null : endtime.trim();
    }

    public String getHandleaction() {
        return handleaction;
    }

    public void setHandleaction(String handleaction) {
        this.handleaction = handleaction == null ? null : handleaction.trim();
    }

    public String getHourrainfall() {
        return hourrainfall;
    }

    public void setHourrainfall(String hourrainfall) {
        this.hourrainfall = hourrainfall == null ? null : hourrainfall.trim();
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km == null ? null : km.trim();
    }

    public String getLimitsegment() {
        return limitsegment;
    }

    public void setLimitsegment(String limitsegment) {
        this.limitsegment = limitsegment == null ? null : limitsegment.trim();
    }

    public String getLimitspeed() {
        return limitspeed;
    }

    public void setLimitspeed(String limitspeed) {
        this.limitspeed = limitspeed == null ? null : limitspeed.trim();
    }

    public String getLinecode() {
        return linecode;
    }

    public void setLinecode(String linecode) {
        this.linecode = linecode == null ? null : linecode.trim();
    }

    public String getLinename() {
        return linename;
    }

    public void setLinename(String linename) {
        this.linename = linename == null ? null : linename.trim();
    }

    public String getMonitorpoint() {
        return monitorpoint;
    }

    public void setMonitorpoint(String monitorpoint) {
        this.monitorpoint = monitorpoint == null ? null : monitorpoint.trim();
    }

    public String getMonitortime() {
        return monitortime;
    }

    public void setMonitortime(String monitortime) {
        this.monitortime = monitortime == null ? null : monitortime.trim();
    }

    public String getMonitorunit() {
        return monitorunit;
    }

    public void setMonitorunit(String monitorunit) {
        this.monitorunit = monitorunit == null ? null : monitorunit.trim();
    }

    public String getMonitoringtime() {
        return monitoringtime;
    }

    public void setMonitoringtime(String monitoringtime) {
        this.monitoringtime = monitoringtime == null ? null : monitoringtime.trim();
    }

    public String getProsegmenttype() {
        return prosegmenttype;
    }

    public void setProsegmenttype(String prosegmenttype) {
        this.prosegmenttype = prosegmenttype == null ? null : prosegmenttype.trim();
    }

    public String getRainfallintensity() {
        return rainfallintensity;
    }

    public void setRainfallintensity(String rainfallintensity) {
        this.rainfallintensity = rainfallintensity == null ? null : rainfallintensity.trim();
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment == null ? null : segment.trim();
    }

    public String getSegmenttype() {
        return segmenttype;
    }

    public void setSegmenttype(String segmenttype) {
        this.segmenttype = segmenttype == null ? null : segmenttype.trim();
    }

    public String getSensorcode() {
        return sensorcode;
    }

    public void setSensorcode(String sensorcode) {
        this.sensorcode = sensorcode == null ? null : sensorcode.trim();
    }

    public String getSnowdepth() {
        return snowdepth;
    }

    public void setSnowdepth(String snowdepth) {
        this.snowdepth = snowdepth == null ? null : snowdepth.trim();
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime == null ? null : starttime.trim();
    }

    public String getTenminuterainfall() {
        return tenminuterainfall;
    }

    public void setTenminuterainfall(String tenminuterainfall) {
        this.tenminuterainfall = tenminuterainfall == null ? null : tenminuterainfall.trim();
    }

    public String getTweentyfourhourrainfall() {
        return tweentyfourhourrainfall;
    }

    public void setTweentyfourhourrainfall(String tweentyfourhourrainfall) {
        this.tweentyfourhourrainfall = tweentyfourhourrainfall == null ? null : tweentyfourhourrainfall.trim();
    }

    public String getUptrackcontrolarea() {
        return uptrackcontrolarea;
    }

    public void setUptrackcontrolarea(String uptrackcontrolarea) {
        this.uptrackcontrolarea = uptrackcontrolarea == null ? null : uptrackcontrolarea.trim();
    }

    public String getUptrackcontrolnum() {
        return uptrackcontrolnum;
    }

    public void setUptrackcontrolnum(String uptrackcontrolnum) {
        this.uptrackcontrolnum = uptrackcontrolnum == null ? null : uptrackcontrolnum.trim();
    }

    public String getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection == null ? null : winddirection.trim();
    }

    public String getWinddirection1() {
        return winddirection1;
    }

    public void setWinddirection1(String winddirection1) {
        this.winddirection1 = winddirection1 == null ? null : winddirection1.trim();
    }

    public String getWinddirection2() {
        return winddirection2;
    }

    public void setWinddirection2(String winddirection2) {
        this.winddirection2 = winddirection2 == null ? null : winddirection2.trim();
    }

    public String getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(String windspeed) {
        this.windspeed = windspeed == null ? null : windspeed.trim();
    }

    public String getWindspeed1() {
        return windspeed1;
    }

    public void setWindspeed1(String windspeed1) {
        this.windspeed1 = windspeed1 == null ? null : windspeed1.trim();
    }

    public String getWindspeed2() {
        return windspeed2;
    }

    public void setWindspeed2(String windspeed2) {
        this.windspeed2 = windspeed2 == null ? null : windspeed2.trim();
    }

    public String getXyes() {
        return xyes;
    }

    public void setXyes(String xyes) {
        this.xyes = xyes == null ? null : xyes.trim();
    }

    public String getXyesw() {
        return xyesw;
    }

    public void setXyesw(String xyesw) {
        this.xyesw = xyesw == null ? null : xyesw.trim();
    }

    public String getXys() {
        return xys;
    }

    public void setXys(String xys) {
        this.xys = xys == null ? null : xys.trim();
    }

    public String getXyss() {
        return xyss;
    }

    public void setXyss(String xyss) {
        this.xyss = xyss == null ? null : xyss.trim();
    }

    public String getXysw() {
        return xysw;
    }

    public void setXysw(String xysw) {
        this.xysw = xysw == null ? null : xysw.trim();
    }

    public String getXyw() {
        return xyw;
    }

    public void setXyw(String xyw) {
        this.xyw = xyw == null ? null : xyw.trim();
    }

    public String getVideostatus() {
        return videostatus;
    }

    public void setVideostatus(String videostatus) {
        this.videostatus = videostatus == null ? null : videostatus.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", actualsnowdepth=").append(actualsnowdepth);
        sb.append(", alarmbasic=").append(alarmbasic);
        sb.append(", alarmconfirmtime=").append(alarmconfirmtime);
        sb.append(", alarmflag=").append(alarmflag);
        sb.append(", alarmlevel=").append(alarmlevel);
        sb.append(", alarmresumeconfirmtime=").append(alarmresumeconfirmtime);
        sb.append(", alarmresumetime=").append(alarmresumetime);
        sb.append(", alarmstatus=").append(alarmstatus);
        sb.append(", alarmvalue=").append(alarmvalue);
        sb.append(", area=").append(area);
        sb.append(", benchmarksnowdepth=").append(benchmarksnowdepth);
        sb.append(", bureaucode=").append(bureaucode);
        sb.append(", bureauname=").append(bureauname);
        sb.append(", continuousrainfall=").append(continuousrainfall);
        sb.append(", cumulativerainfall=").append(cumulativerainfall);
        sb.append(", dayrainfall=").append(dayrainfall);
        sb.append(", dbcreatetime=").append(dbcreatetime);
        sb.append(", description=").append(description);
        sb.append(", devicetype=").append(devicetype);
        sb.append(", downtrackcontrolarea=").append(downtrackcontrolarea);
        sb.append(", downtrackcontrolnum=").append(downtrackcontrolnum);
        sb.append(", dtype=").append(dtype);
        sb.append(", dvalue=").append(dvalue);
        sb.append(", dyss=").append(dyss);
        sb.append(", endtime=").append(endtime);
        sb.append(", handleaction=").append(handleaction);
        sb.append(", hourrainfall=").append(hourrainfall);
        sb.append(", km=").append(km);
        sb.append(", limitsegment=").append(limitsegment);
        sb.append(", limitspeed=").append(limitspeed);
        sb.append(", linecode=").append(linecode);
        sb.append(", linename=").append(linename);
        sb.append(", monitorpoint=").append(monitorpoint);
        sb.append(", monitortime=").append(monitortime);
        sb.append(", monitorunit=").append(monitorunit);
        sb.append(", monitoringtime=").append(monitoringtime);
        sb.append(", prosegmenttype=").append(prosegmenttype);
        sb.append(", rainfallintensity=").append(rainfallintensity);
        sb.append(", segment=").append(segment);
        sb.append(", segmenttype=").append(segmenttype);
        sb.append(", sensorcode=").append(sensorcode);
        sb.append(", snowdepth=").append(snowdepth);
        sb.append(", starttime=").append(starttime);
        sb.append(", tenminuterainfall=").append(tenminuterainfall);
        sb.append(", tweentyfourhourrainfall=").append(tweentyfourhourrainfall);
        sb.append(", uptrackcontrolarea=").append(uptrackcontrolarea);
        sb.append(", uptrackcontrolnum=").append(uptrackcontrolnum);
        sb.append(", winddirection=").append(winddirection);
        sb.append(", winddirection1=").append(winddirection1);
        sb.append(", winddirection2=").append(winddirection2);
        sb.append(", windspeed=").append(windspeed);
        sb.append(", windspeed1=").append(windspeed1);
        sb.append(", windspeed2=").append(windspeed2);
        sb.append(", xyes=").append(xyes);
        sb.append(", xyesw=").append(xyesw);
        sb.append(", xys=").append(xys);
        sb.append(", xyss=").append(xyss);
        sb.append(", xysw=").append(xysw);
        sb.append(", xyw=").append(xyw);
        sb.append(", videostatus=").append(videostatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}