package Erica.tag.ui;

import Erica.tag.MpegInfo;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.text.DecimalFormat;

public class MpegDialog extends TagInfoDialog
{
    private MpegInfo _mpeginfo = null;

    public MpegDialog(String title, int sx, int sy, MpegInfo mi) {
        this(title,-1,-1,sx,sy,mi);
    }

    public MpegDialog(String title, int x, int y, int sx, int sy, MpegInfo mi) {
        super(title);
        _mpeginfo = mi;
        this.setSize(sx,sy);
        if((x!=-1) && (y!=-1)) this.setLocation(x,y);

        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setFont(new Font("Helvetica", Font.PLAIN, 11));
        this.setLayout(gridbag);
        c.fill = GridBagConstraints.BOTH;

        // File/URL Label.
        c.weightx = 1.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        Label flabel = new Label("File/URL : "+_mpeginfo.getLocation());
        gridbag.setConstraints(flabel,c);
        this.add(flabel);

        // Standard Tags.
        c.weightx = 1.0;
        c.gridwidth = GridBagConstraints.RELATIVE;
        Label labeltag = new Label("Standard Tags");
        gridbag.setConstraints(labeltag,c);
        this.add(labeltag);

        // File/URL TextField.
        Label labelinfo = new Label("File/Stream info");
        c.weightx = 1.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridbag.setConstraints(labelinfo,c);
        this.add(labelinfo);

        // Main Panel.
        c.weightx = 1.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        Panel mainPan = new Panel();
        gridbag.setConstraints(mainPan,c);
        this.add(mainPan);
        Panel selectPan = new Panel();
        c.weightx = 1.0;
        gridbag.setConstraints(selectPan,c);
        this.add(selectPan);
        mainPan.setLayout(new GridLayout(1, 2, 0, 1));

        // Mpeg ID3 Tags.
        Panel leftPan = new Panel();
        leftPan.setLayout(new GridLayout(1, 1, 0, 1));
        TextArea area = new TextArea();
        leftPan.add(area);

        if ((_mpeginfo.getTitle() != null) && ((!_mpeginfo.getTitle().equals("")))) area.append("Title="+_mpeginfo.getTitle()+"\n");
        if ((_mpeginfo.getArtist() != null) && ((!_mpeginfo.getArtist().equals("")))) area.append("Artist="+_mpeginfo.getArtist()+"\n");
        if ((_mpeginfo.getAlbum() != null) && ((!_mpeginfo.getAlbum().equals("")))) area.append("Album="+_mpeginfo.getAlbum()+"\n");
        if (_mpeginfo.getTrack() > 0) area.append("Track="+_mpeginfo.getTrack()+"\n");
        if ((_mpeginfo.getYear() != null) && ((!_mpeginfo.getYear().equals("")))) area.append("Year="+_mpeginfo.getYear()+"\n");
        if ((_mpeginfo.getGenre() != null) && ((!_mpeginfo.getGenre().equals("")))) area.append("Genre="+_mpeginfo.getGenre()+"\n");
        
        // Mpeg File info.
        Panel rightPan = new Panel();
        GridBagLayout rightgridbag = new GridBagLayout();
        rightPan.setLayout(rightgridbag);
        GridBagConstraints rightc = new GridBagConstraints();
        rightc.gridx = 0; // note: gridy == RELATIVE
        rightc.fill = GridBagConstraints.HORIZONTAL;
        Label rlabel = null;

        int secondsAmount = Math.round(_mpeginfo.getPlayTime());
        if (secondsAmount < 0) secondsAmount = 0;
        int minutes = (int) (secondsAmount / 60);
        int seconds = (int) (secondsAmount - (minutes * 60));
        rightPan.add(rlabel = new Label("Length : "+minutes+":"+seconds));
        rightgridbag.setConstraints(rlabel, rightc);
        
        DecimalFormat df = new DecimalFormat("#,###,###");
        rightPan.add(rlabel = new Label("Size : "+df.format(_mpeginfo.getSize())+" bytes"));
        rightgridbag.setConstraints(rlabel, rightc);
        rightPan.add(rlabel = new Label(_mpeginfo.getVersion()+" "+_mpeginfo.getLayer()));
        rightgridbag.setConstraints(rlabel, rightc);
        rightPan.add(rlabel = new Label((_mpeginfo.getBitRate()/1000)+" kbps"));
        rightgridbag.setConstraints(rlabel, rightc);
        rightPan.add(rlabel = new Label(_mpeginfo.getSamplingRate()+" Hz "+_mpeginfo.getChannelsMode()));
        rightgridbag.setConstraints(rlabel, rightc);
        rightPan.add(rlabel = new Label("VBR : "+_mpeginfo.getVBR()));
        rightgridbag.setConstraints(rlabel, rightc);
        rightPan.add(rlabel = new Label("CRCs : "+_mpeginfo.getCRC()));
        rightgridbag.setConstraints(rlabel, rightc);
        rightPan.add(rlabel = new Label("Copyrighted : "+_mpeginfo.getCopyright()));
        rightgridbag.setConstraints(rlabel, rightc);
        rightPan.add(rlabel = new Label("Original : "+_mpeginfo.getOriginal()));
        rightgridbag.setConstraints(rlabel, rightc);
        rightPan.add(rlabel = new Label("Emphasis : "+_mpeginfo.getEmphasis()));
        rightgridbag.setConstraints(rlabel, rightc);
        mainPan.add(leftPan);
        mainPan.add(rightPan);
        selectPan.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 4));
        selectPan.add(_close);
    }

    public MpegInfo getOggVorbisInfo()
    {
        return _mpeginfo;
    }

    public static void main(String...a) {
        MpegInfo m = new MpegInfo();
        MpegDialog n = new MpegDialog("Oh god...",0,0,0,0,m);
        n.setSize(400, 400);
        n.setVisible(true);
    }
}