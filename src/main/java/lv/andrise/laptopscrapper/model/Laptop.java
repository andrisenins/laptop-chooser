package lv.andrise.laptopscrapper.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andris on 15.23.8.
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Laptop {

    @Id
    @SequenceGenerator(name = "laptop_id_seq", sequenceName = "laptop_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "laptop_id_seq")
    private long id;
    private String nosaukums;
    private String razotajs;
    private String datoraTips;
    private String ekranaIzmers;
    private String ekranaTips;
    private String IPSEkrans;
    private String maksIzskirtspeja;
    private String procesoraSaime;
    private String procesoraPaaudze;
    private String procesors;
    private String procesoraKodoluSkaits;
    private String cache;
    private String chipset;
    private String uzstaditaisRam;
    private String maxRam;
    private String ramType;
    private int ramSlots;
    private String HDDSize;
    private String HDDSpeed;
    private String SSDSize;
    private String videocardType;
    private String videocard;
    private String videocardMemory;
    private String Wifi;
    private boolean g3;
    private boolean g4;
    private boolean Bluetooth;
    private boolean NFC;
    private boolean WiDi;
    private String USBPorti;
    private boolean HDMI;
    private String Interfeiss;
    private boolean CDRom;
    private String audio;
    private String tiklaKarte;
    private boolean builtInWebcam;
    private boolean builtInMic;
    private String builtInCardReader;
    private String klaviaturasValodas;
    private boolean klaviaturasApgaismojums;
    private boolean mitrumdrosaKlaviatura;
    private String korpusaMaterials;
    private String korpusaKrasa;
    private String izmeri;
    private String svars;
    private String operetajSistema;
    private String standartaBaterija;
    private String baterijasDarbibasLaiks;
    private String drosiba;
    @Column(columnDefinition="TEXT")
    private String ipashasatzimes;
    private String veikals;
    private double aktualaCena;
    private double vecaCena;
    private String url;
    private Date ierakstaDatums;

    public Laptop() {
    }

    public Laptop(String nosaukums, String razotajs, String datoraTips, String ekranaIzmers, String ekranaTips,
                  String IPSEkrans, String maksIzskirtspeja, String procesoraSaime, String procesoraPaaudze,
                  String procesors, String procesoraKodoluSkaits, String cache, String chipset, String uzstaditaisRam,
                  String maxRam, String ramType, int ramSlots, String HDDSize, String HDDSpeed, String SSDSize,
                  String videocardType, String videocard, String videocardMemory, String wifi, boolean g3, boolean g4,
                  boolean bluetooth, boolean NFC, boolean wiDi, String USBPorti, boolean HDMI, String interfeiss,
                  boolean CDRom, String audio, String tiklaKarte, boolean builtInWebcam, boolean builtInMic,
                  String builtInCardReader, String klaviaturasValodas, boolean klaviaturasApgaismojums,
                  boolean mitrumdrosaKlaviatura, String korpusaMaterials, String korpusaKrasa, String izmeri,
                  String svars, String operetajSistema, String standartaBaterija, String baterijasDarbibasLaiks,
                  String drosiba, String ipashasatzimes, String veikals, double aktualaCena, double vecaCena,
                  String url, Date ierakstaDatums) {
        this.nosaukums = nosaukums;
        this.razotajs = razotajs;
        this.datoraTips = datoraTips;
        this.ekranaIzmers = ekranaIzmers;
        this.ekranaTips = ekranaTips;
        this.IPSEkrans = IPSEkrans;
        this.maksIzskirtspeja = maksIzskirtspeja;
        this.procesoraSaime = procesoraSaime;
        this.procesoraPaaudze = procesoraPaaudze;
        this.procesors = procesors;
        this.procesoraKodoluSkaits = procesoraKodoluSkaits;
        this.cache = cache;
        this.chipset = chipset;
        this.uzstaditaisRam = uzstaditaisRam;
        this.maxRam = maxRam;
        this.ramType = ramType;
        this.ramSlots = ramSlots;
        this.HDDSize = HDDSize;
        this.HDDSpeed = HDDSpeed;
        this.SSDSize = SSDSize;
        this.videocardType = videocardType;
        this.videocard = videocard;
        this.videocardMemory = videocardMemory;
        Wifi = wifi;
        this.g3 = g3;
        this.g4 = g4;
        Bluetooth = bluetooth;
        this.NFC = NFC;
        WiDi = wiDi;
        this.USBPorti = USBPorti;
        this.HDMI = HDMI;
        Interfeiss = interfeiss;
        this.CDRom = CDRom;
        this.audio = audio;
        this.tiklaKarte = tiklaKarte;
        this.builtInWebcam = builtInWebcam;
        this.builtInMic = builtInMic;
        this.builtInCardReader = builtInCardReader;
        this.klaviaturasValodas = klaviaturasValodas;
        this.klaviaturasApgaismojums = klaviaturasApgaismojums;
        this.mitrumdrosaKlaviatura = mitrumdrosaKlaviatura;
        this.korpusaMaterials = korpusaMaterials;
        this.korpusaKrasa = korpusaKrasa;
        this.izmeri = izmeri;
        this.svars = svars;
        this.operetajSistema = operetajSistema;
        this.standartaBaterija = standartaBaterija;
        this.baterijasDarbibasLaiks = baterijasDarbibasLaiks;
        this.drosiba = drosiba;
        this.ipashasatzimes = ipashasatzimes;
        this.veikals = veikals;
        this.aktualaCena = aktualaCena;
        this.vecaCena = vecaCena;
        this.url = url;
        this.ierakstaDatums = ierakstaDatums;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNosaukums() {
        return nosaukums;
    }

    public void setNosaukums(String nosaukums) {
        this.nosaukums = nosaukums;
    }

    public String getRazotajs() {
        return razotajs;
    }

    public void setRazotajs(String razotajs) {
        this.razotajs = razotajs;
    }

    public String getDatoraTips() {
        return datoraTips;
    }

    public void setDatoraTips(String datoraTips) {
        this.datoraTips = datoraTips;
    }

    public String getEkranaIzmers() {
        return ekranaIzmers;
    }

    public void setEkranaIzmers(String ekranaIzmers) {
        this.ekranaIzmers = ekranaIzmers;
    }

    public String getEkranaTips() {
        return ekranaTips;
    }

    public void setEkranaTips(String ekranaTips) {
        this.ekranaTips = ekranaTips;
    }

    public String getIPSEkrans() {
        return IPSEkrans;
    }

    public void setIPSEkrans(String IPSEkrans) {
        this.IPSEkrans = IPSEkrans;
    }

    public String getMaksIzskirtspeja() {
        return maksIzskirtspeja;
    }

    public void setMaksIzskirtspeja(String maksIzskirtspeja) {
        this.maksIzskirtspeja = maksIzskirtspeja;
    }

    public String getProcesoraSaime() {
        return procesoraSaime;
    }

    public void setProcesoraSaime(String procesoraSaime) {
        this.procesoraSaime = procesoraSaime;
    }

    public String getProcesoraPaaudze() {
        return procesoraPaaudze;
    }

    public void setProcesoraPaaudze(String procesoraPaaudze) {
        this.procesoraPaaudze = procesoraPaaudze;
    }

    public String getProcesors() {
        return procesors;
    }

    public void setProcesors(String procesors) {
        this.procesors = procesors;
    }

    public String getProcesoraKodoluSkaits() {
        return procesoraKodoluSkaits;
    }

    public void setProcesoraKodoluSkaits(String procesoraKodoluSkaits) {
        this.procesoraKodoluSkaits = procesoraKodoluSkaits;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getUzstaditaisRam() {
        return uzstaditaisRam;
    }

    public void setUzstaditaisRam(String uzstaditaisRam) {
        this.uzstaditaisRam = uzstaditaisRam;
    }

    public String getMaxRam() {
        return maxRam;
    }

    public void setMaxRam(String maxRam) {
        this.maxRam = maxRam;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public int getRamSlots() {
        return ramSlots;
    }

    public void setRamSlots(int ramSlots) {
        this.ramSlots = ramSlots;
    }

    public String getHDDSize() {
        return HDDSize;
    }

    public void setHDDSize(String HDDSize) {
        this.HDDSize = HDDSize;
    }

    public String getHDDSpeed() {
        return HDDSpeed;
    }

    public void setHDDSpeed(String HDDSpeed) {
        this.HDDSpeed = HDDSpeed;
    }

    public String getSSDSize() {
        return SSDSize;
    }

    public void setSSDSize(String SSDSize) {
        this.SSDSize = SSDSize;
    }

    public String getVideocardType() {
        return videocardType;
    }

    public void setVideocardType(String videocardType) {
        this.videocardType = videocardType;
    }

    public String getVideocard() {
        return videocard;
    }

    public void setVideocard(String videocard) {
        this.videocard = videocard;
    }

    public String getVideocardMemory() {
        return videocardMemory;
    }

    public void setVideocardMemory(String videocardMemory) {
        this.videocardMemory = videocardMemory;
    }

    public String getWifi() {
        return Wifi;
    }

    public void setWifi(String wifi) {
        Wifi = wifi;
    }

    public boolean isG3() {
        return g3;
    }

    public void setG3(boolean g3) {
        this.g3 = g3;
    }

    public boolean isG4() {
        return g4;
    }

    public void setG4(boolean g4) {
        this.g4 = g4;
    }

    public boolean isBluetooth() {
        return Bluetooth;
    }

    public void setBluetooth(boolean bluetooth) {
        Bluetooth = bluetooth;
    }

    public boolean isNFC() {
        return NFC;
    }

    public void setNFC(boolean NFC) {
        this.NFC = NFC;
    }

    public boolean isWiDi() {
        return WiDi;
    }

    public void setWiDi(boolean wiDi) {
        WiDi = wiDi;
    }

    public String getUSBPorti() {
        return USBPorti;
    }

    public void setUSBPorti(String USBPorti) {
        this.USBPorti = USBPorti;
    }

    public boolean isHDMI() {
        return HDMI;
    }

    public void setHDMI(boolean HDMI) {
        this.HDMI = HDMI;
    }

    public String getInterfeiss() {
        return Interfeiss;
    }

    public void setInterfeiss(String interfeiss) {
        Interfeiss = interfeiss;
    }

    public boolean isCDRom() {
        return CDRom;
    }

    public void setCDRom(boolean CDRom) {
        this.CDRom = CDRom;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getTiklaKarte() {
        return tiklaKarte;
    }

    public void setTiklaKarte(String tiklaKarte) {
        this.tiklaKarte = tiklaKarte;
    }

    public boolean isBuiltInWebcam() {
        return builtInWebcam;
    }

    public void setBuiltInWebcam(boolean builtInWebcam) {
        this.builtInWebcam = builtInWebcam;
    }

    public boolean isBuiltInMic() {
        return builtInMic;
    }

    public void setBuiltInMic(boolean builtInMic) {
        this.builtInMic = builtInMic;
    }

    public String getBuiltInCardReader() {
        return builtInCardReader;
    }

    public void setBuiltInCardReader(String builtInCardReader) {
        this.builtInCardReader = builtInCardReader;
    }

    public String getKlaviaturasValodas() {
        return klaviaturasValodas;
    }

    public void setKlaviaturasValodas(String klaviaturasValodas) {
        this.klaviaturasValodas = klaviaturasValodas;
    }

    public boolean isKlaviaturasApgaismojums() {
        return klaviaturasApgaismojums;
    }

    public void setKlaviaturasApgaismojums(boolean klaviaturasApgaismojums) {
        this.klaviaturasApgaismojums = klaviaturasApgaismojums;
    }

    public boolean isMitrumdrosaKlaviatura() {
        return mitrumdrosaKlaviatura;
    }

    public void setMitrumdrosaKlaviatura(boolean mitrumdrosaKlaviatura) {
        this.mitrumdrosaKlaviatura = mitrumdrosaKlaviatura;
    }

    public String getKorpusaMaterials() {
        return korpusaMaterials;
    }

    public void setKorpusaMaterials(String korpusaMaterials) {
        this.korpusaMaterials = korpusaMaterials;
    }

    public String getKorpusaKrasa() {
        return korpusaKrasa;
    }

    public void setKorpusaKrasa(String korpusaKrasa) {
        this.korpusaKrasa = korpusaKrasa;
    }

    public String getIzmeri() {
        return izmeri;
    }

    public void setIzmeri(String izmeri) {
        this.izmeri = izmeri;
    }

    public String getSvars() {
        return svars;
    }

    public void setSvars(String svars) {
        this.svars = svars;
    }

    public String getOperetajSistema() {
        return operetajSistema;
    }

    public void setOperetajSistema(String operetajSistema) {
        this.operetajSistema = operetajSistema;
    }

    public String getStandartaBaterija() {
        return standartaBaterija;
    }

    public void setStandartaBaterija(String standartaBaterija) {
        this.standartaBaterija = standartaBaterija;
    }

    public String getBaterijasDarbibasLaiks() {
        return baterijasDarbibasLaiks;
    }

    public void setBaterijasDarbibasLaiks(String baterijasDarbibasLaiks) {
        this.baterijasDarbibasLaiks = baterijasDarbibasLaiks;
    }

    public String getDrosiba() {
        return drosiba;
    }

    public void setDrosiba(String drosiba) {
        this.drosiba = drosiba;
    }

    public String getIpashasatzimes() {
        return ipashasatzimes;
    }

    public void setIpashasatzimes(String ipashasatzimes) {
        this.ipashasatzimes = ipashasatzimes;
    }

    public String getVeikals() {
        return veikals;
    }

    public void setVeikals(String veikals) {
        this.veikals = veikals;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getIerakstaDatums() {
        return ierakstaDatums;
    }

    public void setIerakstaDatums(Date ierakstaDatums) {
        this.ierakstaDatums = ierakstaDatums;
    }

    public double getAktualaCena() {
        return aktualaCena;
    }

    public void setAktualaCena(double aktualaCena) {
        this.aktualaCena = aktualaCena;
    }

    public double getVecaCena() {
        return vecaCena;
    }

    public void setVecaCena(double vecaCena) {
        this.vecaCena = vecaCena;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", nosaukums='" + nosaukums + '\'' +
                ", razotajs='" + razotajs + '\'' +
                ", datoraTips='" + datoraTips + '\'' +
                ", ekranaIzmers='" + ekranaIzmers + '\'' +
                ", ekranaTips='" + ekranaTips + '\'' +
                ", IPSEkrans='" + IPSEkrans + '\'' +
                ", maksIzskirtspeja='" + maksIzskirtspeja + '\'' +
                ", procesoraSaime='" + procesoraSaime + '\'' +
                ", procesoraPaaudze='" + procesoraPaaudze + '\'' +
                ", procesors='" + procesors + '\'' +
                ", procesoraKodoluSkaits='" + procesoraKodoluSkaits + '\'' +
                ", cache='" + cache + '\'' +
                ", chipset='" + chipset + '\'' +
                ", uzstaditaisRam='" + uzstaditaisRam + '\'' +
                ", maxRam='" + maxRam + '\'' +
                ", ramType='" + ramType + '\'' +
                ", ramSlots=" + ramSlots +
                ", HDDSize='" + HDDSize + '\'' +
                ", HDDSpeed='" + HDDSpeed + '\'' +
                ", SSDSize='" + SSDSize + '\'' +
                ", videocardType='" + videocardType + '\'' +
                ", videocard='" + videocard + '\'' +
                ", videocardMemory='" + videocardMemory + '\'' +
                ", Wifi='" + Wifi + '\'' +
                ", g3=" + g3 +
                ", g4=" + g4 +
                ", Bluetooth=" + Bluetooth +
                ", NFC=" + NFC +
                ", WiDi=" + WiDi +
                ", USBPorti='" + USBPorti + '\'' +
                ", HDMI=" + HDMI +
                ", Interfeiss='" + Interfeiss + '\'' +
                ", CDRom=" + CDRom +
                ", audio='" + audio + '\'' +
                ", tiklaKarte='" + tiklaKarte + '\'' +
                ", builtInWebcam=" + builtInWebcam +
                ", builtInMic=" + builtInMic +
                ", builtInCardReader='" + builtInCardReader + '\'' +
                ", klaviaturasValodas='" + klaviaturasValodas + '\'' +
                ", klaviaturasApgaismojums=" + klaviaturasApgaismojums +
                ", mitrumdrosaKlaviatura=" + mitrumdrosaKlaviatura +
                ", korpusaMaterials='" + korpusaMaterials + '\'' +
                ", korpusaKrasa='" + korpusaKrasa + '\'' +
                ", izmeri='" + izmeri + '\'' +
                ", svars='" + svars + '\'' +
                ", operetajSistema='" + operetajSistema + '\'' +
                ", standartaBaterija='" + standartaBaterija + '\'' +
                ", baterijasDarbibasLaiks='" + baterijasDarbibasLaiks + '\'' +
                ", drosiba='" + drosiba + '\'' +
                ", ipashasatzimes='" + ipashasatzimes + '\'' +
                ", veikals='" + veikals + '\'' +
                ", aktualaCena=" + aktualaCena +
                ", vecaCena=" + vecaCena +
                ", url='" + url + '\'' +
                ", ierakstaDatums=" + ierakstaDatums +
                '}';
    }
}
