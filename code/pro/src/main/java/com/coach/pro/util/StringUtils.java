package com.coach.pro.util;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author wanghui
 * @date 2021/08/30 11:23
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static List<String> retail = Arrays.asList(new String[]{"C3464 B4RH3", "C3464 B4SHG", "C3921 B4SBV", "4615 B4RH3", "4615 B4RH5", "C0712 B4S0M",
            "C3756 B4S0M", "C3756 B4SHG", "C3762 B4RH3", "4616 B4RH3", "4616 B4RH5", "4616 B4S0M", "C3760 B4SHG", "C4656 B4SBV",
            "C3841 B4SN5", "4593 V5RGH", "4594 B4RGL", "4594 B4SI1", "C0750 V5SI3", "C2418 B4SI0", "C5061 B4SN6", "4603 B4RGL", "C0770 B4SI0",
            "C2449 B4S9J", "C2588 B4SBW", "C2588 B4SBX", "C2588 B4SHG", "C2583 B4NQ4", "3642 B4RA3", "C3853 B4RH3", "C3853 B4SHG", "5868 B4RXM",
            "5868 B4RXN", "89143 B4RGL", "89143 B4RTQ", "89143 B4SI1", "89143 V5PUS", "89145 V5PUU", "C0081 B4AA8", "C2791 B4R7B", "C2791 B4SI1", "C3865 B4RH3",
            "C3865 B4SHG", "C3866 B4SN6", "C5017 B4SBV", "89355 V5PUV", "C3282 B4RH3", "C3282 B4SBV", "C3282 B4SBX", "C3282 B4SHG", "89144 B4RGL", "89144 V5PUV",
            "89146 V5PUS", "3492 B4NQ4", "C2584 B4SBI", "88236 B4AA8", "4619 B4RH3", "4619 B4RH5", "4679 MWQV8", "C3758 B4NQ4", "C3758 V5SNE",
            "C2462 MWQV8", "1591 IMRFF", "4606 V5RGT", "4606 V5RGU", "6793 B4NQ4", "91215 B4OOH", "91215 B4R7B", "91215 LHPVT", "4774 V5RGT",
            "148 B4P2M", "C3889 B4NQ4", "89224 V5PUJ", "C3890 B4NQ4", "C2267 V5AZU", "C2745 B4NQ4", "C3463 B4/BK", "C3463 V5AZU", "C3922 B4/HA",
            "3928 B4/BK", "3928 B4/IY", "3928 V5GRT", "C0714 V5FOR", "C2277 B4/IY", "C2277 V5SAR", "C2277 V5TAU", "4617 V5RRJ", "4617 V5RYG",
            "C3738 B4BCY", "C3738 B4/IY", "C3738 B4/BK", "C2587 V5SAR", "C2587 V5AZU", "C2587 B4/IY", "C0806 B4/BK", "C0715 B4S0K", "4790 B4PWQ",
            "C2595 V5LBO", "C2595 B4SBA", "C2595 B4EWK", "4597 V5GRT", "4597 B4/WN", "4597 B4/HA", "4595 B4RGI", "C3738 V5NAT", "C3738 V5GRT", "C3738 B4SAR",
            "C0753 V5NB2", "C0753 B4BAR", "6892 B4/IY", "6210 B4SBB", "6210 B4MZI", "4602 V5SBC", "4600 B4/IY", "4600 B4/BK", "C3838 B4SAR",
            "89088 B4SBG", "89088 B4SBF", "88346 V5AZU", "35844 V5STZ", "35844 B4SU2", "35844 B4RDR", "29411 GM/AZ", "C3941 B4B07", "C3840 B4RHO",
            "C2278 V5GRT", "C2263 V5TAU", "C3875 B4RHR", "C2264 B4/NA", "C2264 B4/GN", "C3856 B4MBV", "C2265 B4/NA", "C2265 B4/IY", "C0302 B4RYP",
            "C5026 V5LBO", "C5026 B4AFF", "C3914 B4RJB", "C2781 B4RRJ", "C2781 B4/GN", "C0776 B4SI0", "C3851 V5OR4", "C3850 B4AFF", "C2582 B4SB7",
            "C3861 B4RHR", "C0774 B4RH0", "C3859 B4RHR", "C3859 B4MBV", "C0777 B4CAH", "C3863 V5LBO", "C3860 B4RHR", "C3461 B4CAH", "C3461 B4AFF",
            "612 B4CAH", "4653 V5LMF", "4653 B4/TE", "608 LHTAU", "607 B4MZI", "78800 V5QUF", "78800 B4S9V", "78800 B4LKE", "78800 B4/HA",
            "C0800 B4FOR", "4690 B4/TP", "4690 B4/BK", "88231 B4PN6", "89070 V5PTO", "89070 B4SBG", "89070 B4SBF", "89066 B4/HA", "88342 B4/BK",
            "C0799 V5GRT", "C0799 B4FOR", "5211 V5S9W", "5211 V5AZU", "5211 B4/WN", "5211 B4/BK", "4684 V5BLK", "4684 B4/HA",
            "C0801 B4FOR", "C0803 B4N5M", "C0802 V5NGU", "C0300 B4RYQ", "C2111 B4NB2", "C0704 B4S0L", "C0704 B4CAH", "C2592 B4/IY", "C0801 V5GRT",
            "C0804 B4SI0", "C0638 B4GRT", "C0638 B4MPL", "C0638 V5FOR", "C0721 B4RH5", "C0721 B4SHG", "C0721 B4SBW", "C0721 B4SBV", "C0721 B4S0M",
            "7168 B4/HA", "5601 V5BLK", "C3880 V5S5X", "C3880 V5GRT", "C3880 LHM97", "C3880 B4/U6", "C3880 B4/IY", "C3880 B4/BK", "C2275 B4L4A",
            "76105 V5NGU", "76105 B4SB7", "76105 B4SB5", "73995 V5TWI", "73995 V5RGJ", "73995 B4P1Y", "73995 B4EB1", "73995 B4BHP", "73995 B4/LJ",
            "C0773 B4/GN", "636 V5FOR", "635 V5NGU", "C3888 B4SN7", "C0772 B4S9U", "C0772 B4/IY", "C0772 B4/GN", "C0772 B4/BK", "76105 V5NWQ",
            "C3460 B4/BK", "C4700 B4/NP", "C2586 V5SBK", "C2586 V5SBJ", "C3887 B4SN7", "C0773 B4S9V", "C0773 B4RRJ", "C0773 B4/U6", "C0773 B4/IY",
            "C3460 B4/HA", "C3460 V5AZU", "C3766 B4CAH", "C2621 B4/BK", "C2590 B4CAH", "C3881 B4/AZ", "C0823 B4/BK", "C3891 B4/AZ", "C3766 B4EFH",
            "6807 V5DPK", "C2773 B4/NA", "C2774 B4/NA", "C3970 GDDEN", "C0669 SVDTV", "C3968 GDDEN", "C0671 GD/HA", "C0692 V5EWK", "C0690 V5FOR",
            "79341 V5L4A", "4813 B4SHG", "4813 B4S0M", "4813 B4RH5", "4813 B4RH3", "89223 B4/HA", "C3867 B4SNJ", "C3868 B4SNJ", "C3868 B4SNI",
            "C3937 B4SN9", "4866 B4RH3", "86107 B4RGL", "31541 B4NQ4", "C2627 B4NQ4", "C0831 B4SMW", "C0831 B4SI0", "4866 B4S0M", "4866 B4RH5",
            "5476 B4RH3", "32445 V5RJ8", "32445 LHPVT", "3621 B4/CB", "89399 B4NQ4", "C0135 BGNGN", "C0135 B4SHG", "C0135 B4SBX", "C0135 B4SBV",
            "4875 B4/HA", "C3938 B4/HA", "6909 B4NQ4", "C2325 B4SHG", "C2325 B4SCJ", "5476 B4SHG", "5476 B4SCJ", "5476 B4SBX", "5476 B4RH5",
            "C2697 B4SB8", "C2617 V5SBD", "4763 V5GRT", "4763 B4RRJ", "4763 B4BCY", "4763 B4/HA", "4761 B4RGI", "C2690 V5TAU", "4875 V5BLK",
            "C0842 V5NB2", "C0840 B4CAH", "C3821 B4S9W", "C0851 B4/WN", "C0851 B4/BK", "3238 V5S9W", "3238 B4/BK", "C2626 B4SB8", "C2626 B4SB7",
            "57841 LHM97", "57841 B4S9V", "C4188 B4S9W", "88484 V5S9W", "1103 V5CHK", "C3486 B4SAR", "C3486 B4/NA", "C3486 B4/HA", "C3486 B4/BK",
            "C2643 B4SAR", "C2643 B4/NA", "C2643 B4/BK", "C1216 V5NB2", "22952 B4/U6", "C1220 B4MVZ", "57841 V5PO", "57841 V5OFU", "57841 V5AZU",
            "810 V5CHK", "76199 V5EWK", "76199 B4SB5", "76199 B4SB4", "79837 V5EWK", "79837 B4QUK", "7110 B4/HA", "6908 V5BLK", "4792 B4/WN",
            "C2682 JICHR", "C2673 OLOAK", "C2673 OLNAV", "C2670 JILK4", "C2670 JICHR", "4553 OLOT8", "73579 JIKHA", "C2632 V5SBJ", "C2632 V5SB8",
            "C1052 JICHR", "3102 QBCHR", "C2693 JICHR", "5079 JIQUA", "39775 QBCHR", "C1063 OLSMR", "C3802 OLSMR", "C1059 JIS2K", "C2681 JICHR",
            "C3944 OLOQY", "C2286 JISBO", "C2286 JIOBJ", "C2286 JIBON", "C2286 JIBLK", "86929 OLCWH", "7307 OLBLK", "C0931 JIS2E", "C1053 JICHR",
            "C4673 OLSV0", "C4673 OLSUZ", "C4673 OLSFB", "4556 OLBLK", "C4674 OLSUY", "C4674 OLSFB", "C3942 OLSAE", "C2235 OLHAZ", "C3804 JIBLK",
            "C1058 JIBLK", "C0239 JISAQ", "C0239 JIROT", "C0239 JIBLK", "C0241 JIBLK", "C0243 JIROT", "C0243 JIOBJ", "C0243 JIBLK", "C0244 JIBLK",
            "C2663 JISMV", "C2663 JISMN", "C2663 JISB3", "C2663 JISB2", "C2663 JISB1", "C2291 JIBLK", "C2675 JISAR", "C2675 JIOBJ", "C2675 JICWH",
            "C2662 JISB3", "C2662 JISB2", "C2662 JISB1", "C2284 JIBLK", "C2665 JISB3", "C2665 JISB1", "C2292 JIBLK", "C2668 JISBN", "C2668 JIBHP",
            "C2694 JICWH", "C2694 JIBHP", "55565 JIBLK", "76193 JIP2V", "58097 QB/BK", "C2293 JIBLK", "C2667 JISBN", "C2662 JISMV", "C2662 JISMN",
            "93820 OLSAD", "93820 LHMA0", "93820 JIBLK", "C0673 OLNHM", "93817 JIQBM", "93817 JIBHP", "C1073 QB/BK", "79544 QB/BK", "C1071 QB/BK",
            "C3795 JIO1U", "C3801 JISMQ", "C3803 JISMQ", "C3803 JIEFH", "4072 JICHR", "4072 JIA7U", "C3799 JISMQ", "C3798 JISMN", "4884 JIBLK",
            "C1097 JIS2O", "C1097 JIS2N", "4892 JIBLK", "4085 JIBLK", "C3793 JISMT", "C3793 JIO1U", "C3794 JISMT", "C3794 JIO1U", "C3795 JISMT",
            "C3427 B4/M2", "C0851 B4/U6", "C0851 B4/HA", "C0695 B4CAH", "C0694 B4NQ4", "76199 B4AFF", "4892 JIBLK", "4085 JIBLK", "C0931 JIS2E",
            "C3824 B4SHG", "C3824 B4RH3", "C3490 B4S9W", "C3490 B4M97", "C3490 B4/U6", "C3490 B4/HA", "C3427 B4EWK", "C3427 B4CAH", "C3427 B4/U6",
            "C3845 B4/AZ", "C3844 B4/U6", "C3844 B4/HA", "C3844 B4/BK", "C3843 B4S9W", "C3843 B4OFU", "C3843 B4GRT", "C3833 B4RJB", "C3833 B4/IO",
            "C4688 B4SUX", "C4685 B4/HA", "C4678 B4SN7", "C4676 B4SUX", "C4675 B4SUX", "C4674 OLSV0", "C4672 B4SUX", "C3845 B4M97", "C3845 B4/U6",
            "C5016 B4SUX", "C4704 B4SUX", "C4694 B4SUX", "C4693 B4SUX", "C4692 B4SUX", "C4691 B4SUX", "C4690 B4SUX", "C4689 B4SUX"
    });


    public static List<String> outlet = Arrays.asList(new String[]{
            "C0931 JIS2E", "4085 JIBLK", "4892 JIBLK", "C2817 IMD9S", "F48740 IME74", "F80068 IMPGZ", "C1426 SVRFI", "C3148 IMAA8", "F76691 IMAA8", "87708 IMPK4", "C2185 IMOT4", "C4074 IMSPW", "79573 B4NQ4", "5696 IMCZ9", "5696 IME74", "5696 IMRVQ", "5698 SVROH", "5671 IMAA8", "5671 IMDJ8", "6548 IMAA8", "6548 IMDJ8", "1695 SKHBK", "C3654 SVPVW", "76078 B4OOH", "76078 B4P0A", "1912 IMQZV", "1912 IMR1U",
            "1912 IMR2P", "1912 SVRT6", "C1541 QBS64", "C2822 IMDEI", "1955 IMDQC", "4113 IMQZV", "C2826 IMDEI", "5483 IMQZV", "C2825 IMDEI", "1917 IMQZV", "1917 IMR1U", "1917 IMR2P", "C2823 IMDEI", "C1548 IMQZV", "C2827 IMDEI", "1613 IMRFF", "C1430 IMAA8", "C1430 IMDQC", "C1430 IMS5J", "79025 V5AA8", "79609 IMRFF", "79609 IMRVQ", "79609 QBR6K", "F79609 IMAA8", "F79609 IMCBI", "F79609 IMDQC", "F79609 IME74",
            "F79609 SVPK1", "C1421 IMSDD", "C3415 SVSDE", "5498 IMRL7", "C2806 IMAA8", "C2806 IMDQC", "C2806 IMQQ4", "C3593 IMRL7", "C4066 IMDEI", "5509 IMRKY", "5566 IMCOB", "C4067 IMQQ4", "6192 IMRKY", "6504 IMAA8", "C4059 IMBLK", "C4059 IMDEN", "C4059 IMM6H", "C4060 IMBLK", "C4060 IMDEN", "89068 B4PVR", "5459 B4RKK", "F81992 IMAA8", "1503 QBE7V", "F90782 IMAA8", "68168 IMN91", "68168 SV/C9", "C2180 IMOT4",
            "C2808 IMLOT", "3011 IMR68", "6167 QBE7V", "76622 IMCBI", "76622 IMDQC", "C2002 IME7V", "C2816 IMD9S", "C4922 IMDEI", "2558 IMDQC", "2558 IMRFF", "2558 IMRZH", "78277 QBR6K", "1424 IMRKY", "6168 QBE7V", "90400 IME7V", "90400 IMOT4", "91019 IMCBI", "C1539 IMNOX", "C2174 IMOT4", "C0468 IMRL7", "83959 IML72", "91016 IME7V", "91495 IMAA8", "91495 IMCBI", "C1563 IMNOX", "C4921 IMDEI", "F79993 IMAA8",
            "F73293 IMAA8", "F73293 IMDQC", "38302 IMAA8", "1665 IMAA8", "1665 IMCBI", "1665 IMQRF", "1665 IMRVQ", "C2876 IMSDJ", "3042 IMRFF", "3045 IMR0Q", "75501 B4/MX", "75501 B4/SN", "703 B4NQ4", "6165 QBE7V", "83607 IMRFF", "83607 IMRVQ", "83607 QBR6K", "F83607 IMCBI", "F83607 IMDQC", "1591 IMAA8", "1591 IMCBI", "6512 QBE7V", "91384 IME7V", "91494 IMAA8", "91494 IMDQC", "91494 IMRVQ", "C1786 IMNOX",
            "2312 IMAA8", "2312 IMRFF", "2312 IMRO8", "870 B4OOH", "C2820 IMNOX", "F76620 IMDJ8", "91512 IMAA8", "91512 IMCBI", "91512 IMRVQ", "F76636 IMAA8", "F76636 SKHLL", "C2819 IMS5U", "C2814 IMBLK", "C2814 IMCHK", "C2814 IMTEA", "C2814 SVSCV", "F57526 SV/BK", "F48733 IMLON", "C2872 IMBLK", "C2872 IMS9M", "C2872 SV/A5", "C2872 SV/PW", "F75516 IMBLK", "C2186 IMA47", "C2187 SVHGR", "C2244 IMFCG",
            "C4073 IMTAP", "78217 GDDPK", "76089 GDMVS", "55200 GDDPK", "55200 GMPE4", "2348 SV/BK", "5666 IMBLK", "6488 IMF8Q", "91065 SV/BK", "73545 V5M55", "79326 B4/M2", "C2828 IMCHK", "C2828 IMFUS", "C2828 IMSE1", "1959 IMBLK", "C2004 IMCHK", "C2004 IMSE1", "C2829 IMCHK", "C2829 IMFUS", "C1789 IMRVQ", "69654 B4E7S", "35617 B4BHP", "69613 V5OR4", "40862 GDOXB", "40862 GM/BK", "79316 GMBOY", "79316 GMMAR",
            "1612 SV/MQ", "F31399 SV/ET", "C1429 IMS3U", "C1432 IMBLK", "C1432 IMCHK", "C1432 IMPEC", "C1432 IMS9M", "C1432 SVOFU", "C1432 SVPE4", "C2176 IMS8O", "C2857 IMS7D", "C2863 IMCAH", "C4068 IMBLK", "C4068 IMCHK", "C4068 IMTAU", "C4068 SVSDK", "68380 B4/BK", "68380 B4/HA", "76534 B4P27", "68555 V5M55", "69663 B4OQZ", "79608 SV/M4", "79608 SVRZG", "F79608 IMBLK", "F79608 IMLQD", "F79608 IMMID",
            "C2871 SVR4C", "5500 IMBLK", "5500 IMLR0", "5500 SVRCK", "5503 IMBLK", "5503 SVRCK", "5506 IMCHK", "C2803 IMCHK", "C2803 SVQVE", "C2804 IMLL9", "C2805 QBDB7", "C3596 IMTAP", "5493 IMPEC", "5497 IMCHK", "5555 IMCAH", "5567 SV/CT", "5568 QBRL1", "5605 IMRL7", "6019 QBRO5", "6020 IMRO9", "C1530 IMS3T", "C1531 IMRZG", "C2801 IMCHK", "C2801 SVQVE", "C4065 IMSPV", "6015 IMCAH", "6503 IMCHK", "C1769 IMRZG",
            "73549 GDBLK", "53352 B4OXB", "C4056 IMSQN", "C4164 IMSQQ", "C4058 IMSQP", "C4063 IMSQN", "C4062 IMSQP", "C4062 IMSQQ", "88342 V5BOY", "89066 B4/RN", "89066 V5PMY", "1937 IMR0A", "1938 IMBLK", "2341 SVQZZ", "2733 IMBLK", "91025 OLQUG", "91393 OLQE7", "F91070 SVQED", "90399 IMBLK", "1904 SV/MQ", "3041 QBRO5", "75818 IMLQD", "91082 SVA47", "C1569 IMBLK", "C1569 IMCHK", "C2812 IMF8Q", "C1302 IMBLK",
            "C1302 IMRAI", "1898 IMBLK", "76624 IMPEC", "C2815 IMBLK", "C2815 IMCHK", "C2815 IMTEA", "C2815 QBRM1", "C2815 SVSCV", "2553 IMCHK", "2308 IMS7D", "2308 IMTAP", "2308 QBEJ9", "2561 IMBLK", "2561 IMCHK", "91174 IMBLK", "91174 SVPE4", "C1445 QBOXB", "C2858 IMCAH", "C3595 QBDB7", "C1537 QBHGR", "C2175 IMS8O", "C2807 QBEJ9", "6191 SV/CT", "F48619 IMBLK", "91051 IMCAH", "91493 IMBLK", "91493 SVR2O",
            "C1784 SVL7C", "651 B4/BK", "78153 B4/BK", "C1558 IMBLK", "C1559 IMKEL", "C1560 IMCAH", "C1650 IMS60", "C2836 IMCAH", "F79994 IMBLK", "1573 SVM7Q", "79243 V5BLK", "F73277 IMEUZ", "F73277 IMLON", "5691 IMS7D", "C2184 IMFFL", "F28977 QBROS", "F44962 SV/F9", "1671 IMF2L", "1671 IMR98", "1671 IMRF6", "1671 QBRVE", "C2875 IMCHK", "C2875 IMPPK", "C2875 IMSDK", "91079 SVA47", "79997 SVR2O", "91603 SVF23",
            "F79999 IMBLK", "F88561 SV/BK", "1316 IMR2E", "4343 IMR19", "75698 SVOR3", "697 B4/HA", "C2853 IMBLK", "C2853 SV/E7", "3075 QBRO5", "F79946 IMBLK", "F79946 IMCHK", "F28976 SV/F9", "1586 IMCAH", "1589 IMCHK", "1589 IMLR0", "79234 B4PE2", "F75514 IMBLK", "F75514 SV/QU", "F75514 SVC2J", "F75514 SVP4Z", "91146 IMBLK", "91146 IMCHK", "91146 IMR1X", "91146 SV/A5", "1011 IMLON", "1011 IMOVG", "1011 IMS75",
            "1011 IMSJX", "1011 IMSJZ", "F44958 IMMID", "F44958 IMORW", "F44958 SV/ET", "F44958 SV/F9", "F44958 SV/XR", "F55600 SV/CO", "F78751 IMBLK", "69507 B4/HA", "78503 B4CAH", "79353 B4PE2", "79601 B4OXB", "3787 LHTAP", "635 B4QUK", "C1435 IMS3U", "C1802 QBRM1", "C2821 IMSCW", "76618 IMROS", "F76618 IMBLK", "6023 QBRPF", "91122 IMLON", "91122 IMOVG", "91122 IMS74", "91122 SVR2H", "91168 IMQAB", "91168 IMQAC",
            "F72673 IMBLK", "79399 B4/ST", "78500 B4/BK", "78501 B4P27", "78165 B4PBT", "78484 B4AX2", "78484 B4P27", "78486 V5OR7", "79468 B4/ST", "79468 B4PE2", "79474 V5O4R", "79293 B4PF9", "79295 B4/BK", "79295 V5N1A", "79298 V5CAD", "79401 B4PE2", "C2818 IMBLK", "C2818 IMCHK", "C2818 IMDB7", "141 B4/HA", "141 B4EMK", "F80821 IMBLK", "F80821 IMPIN", "391 SVB3R", "C1550 SVS66", "C1551 SVS66", "68539 B4OQX", "87998 BBKBK",
            "5603 IMRL8", "C1525 IMA47", "91110 SVQB8", "C2831 IMDEI", "C2832 IMDEI", "91492 SVQE6", "91094 SVM64", "2782 IMR8X", "2850 IMR90", "2850 SVR92", "C3100 IMCAH", "91093 SVM64", "79358 BBKBK", "236 IMQBE", "236 SVQB8", "91136 SVQE6", "C4208 IMDEN", "C2299 IMD9S", "C2299 IMDJ8", "91178 IMRZH", "C1870 IMOSN", "C4466 IMDEN", "C3326 IMOT4", "7287 IMAA8", "7287 IMDJ8", "C4212 IMDEI", "C1715 SVOT8", "C3335 IMDEI", "91677 IMD9S",
            "6262 SVRON", "C3050 IMOTV", "C4477 IMDEN", "C4477 IMM6H", "C3308 IMBDX", "C3308 IMRVQ", "C1709 IME7V", "C2943 IMDEI", "C0058 IMAA8", "C2298 IMTEA", "C2298 QBRM1", "C2256 IMFFL", "58032 IMPEV", "58032 QBDB7", "6959 IMS9M", "C4462 IMCHK", "C2192 IMBLK", "6925 IMBLK", "3888 IMCHK", "C4464 IMSQQ", "87734 QBDB7", "C3404 IMS7D", "C3406 SV/EW", "C3058 IMCAH", "C1985 IMBLK", "C4653 IMSQP", "C3713 IMDEI", "7249 IMCAH", "C3325 IMDEI",
            "C3053 IMCAH", "C2937 IMDEI", "C3355 IMCAH", "C3332 IMDEI", "C0039 IMCAH", "C1884 SVS66", "C2957 QBE7V", "88328 JIPMZ", "4176 JIO79", "89952 JIO79", "4182 JIO79", "F83000 QB/M2", "F72984 JIO79", "589 JIO79", "F72989 JIO79", "C1297 QBS5Y", "C1313 QBS7B", "2377 QBTAM", "599 QBQBK", "89918 QBTN2", "534 QBTN2", "89909 QBTN2", "C1329 QBS7B", "1711 OLQRX", "C1411 QBMI5", "C3228 QBE7V", "C2935 QBMI5", "C3230 QBE7V", "F38755 QBTN2",
            "2485 QBCAH", "4118 QBE7V", "4189 QBTN2", "C2813 QBSIH", "C4149 QBKHA", "C4149 QBMI5", "F50715 QBAF4", "89935 QBQ9J", "580 QBTN2", "C4035 B4/DE", "4010 QBMI5", "573 QBMI5", "55549 QBCHR", "6787 QBTN2", "6788 QBTN2", "C2963 QBSDQ", "756 JIQX3", "760 JIQVW", "C2837 QBMI5", "F88336 QBPL4", "1909 QBTN2", "2942 QBAF4", "C4145 QBAF4", "C4146 QBSP8", "5623 QBQBE", "C2060 QBS7S", "5665 QBQBE", "5606 QBQBE", "91375 JIPW0", "C2712 QBMI5",
            "C4139 QBSP8", "C2724 QBSID", "C3765 QBMI5", "C4140 QBSP8", "C3747 QBMI5", "C4141 QBSP8", "C2711 QBMI5", "C1281 QBS4O", "C2949 QBE7V", "C4135 QBCHK", "C4135 QBDF5", "C2952 QBE7V", "192 JIQUA", "F78777 QBMI5", "C4138 QBCHK", "C4138 QBDF5", "C4138 QBSP4", "F84711 QBTN2", "2736 QBAF4", "3001 QBOH0", "C2241 QBS8T", "91485 QBMI5", "91485 QBTN2", "2853 QBAF4", "C1290 QBS5D", "C1293 QBS4A", "C1486 QBS4A", "182 JIQUA", "182 OLQRX",
            "C0931 JIS2E", "93819 JIBLK", "4179 NIBHP", "F72973 NIBLK", "F72974 NIHGR", "89954 NIBLK", "91303 NIBHP", "4183 NIBLK", "F73419 NIBLK", "89917 QB/BK", "89915 QBNI9", "89908 QB/BK", "207 OLBLK", "C1364 QBS4V", "C4148 QB/BK", "F39946 QB/BK", "F39946 QB/SD", "89934 QB/BK", "89934 QB/SD", "C1361 QBS4V", "1981 QBQRG", "F75757 QB/BK", "F75757 QBBHP", "C1265 QB/BK", "C1265 QBN08", "C2902 QBLNN", "C1277 QB/BK", "C3748 QBLNN",
            "C1269 QB/BK", "C1269 QBRM1", "C2908 QB/MQ", "C2906 QB/BK", "C2906 QBN08", "C2907 QBLNN", "4009 QB/BK", "4011 QB/BK", "4006 QB/BK", "4007 QB/BK", "4007 QB/SD", "89896 QB/BK", "89896 QB/CT", "2389 QBQZN", "88892 QB/BK", "88892 QB/CT", "55575 SV/BK", "54857 SV/BK", "55570 QBBHP", "55550 QB/BK", "72277 QBBHP", "780 QBQBM", "79544 QBLQD", "6786 QB/BK", "6786 QBPDU", "78830 JIBLK", "78830 JICHK", "78830 JICRD", "91761 JIBLK",
            "78832 JICHK", "78832 JIPDU", "93849 JIBLK", "C2833 QB/BK", "F88870 QB/BK", "F88273 QB/BK", "C4144 QB/BK", "C1401 QBF8Q", "75609 JIBLK", "75609 JIMF1", "77846 JIBLK", "78945 JIMJU", "78899 JIMMV", "89079 JIBLM", "76143 JIP2S", "79045 JIPDW", "1960 QBQZG", "C2710 QB/BK", "C2864 QBSDL", "C4019 QBSP5", "C4019 QBSP6", "C2716 QB/BK", "C4022 QBSP5", "C4022 QBSP6", "C2715 QB/BK", "C4021 QBSP5", "C4021 QBSP6", "C1280 QB/BK",
            "C1280 QB/UJ", "C1280 QBF8Q", "C1280 QBHGR", "C1280 QBPDU", "C4134 QBSP2", "C4134 QBSP3", "C2950 QB/BK", "193 JIEMK", "C4137 QBCHK", "C4137 QBRM1", "C4137 QBSMB", "F84710 QB/BK", "2854 QB/BK", "2854 QBBHP", "91484 QB/BK", "2540 QB/BK", "2540 QBBHP", "3180 QBR64", "C1291 QBS4B", "C1766 QB/BK", "C1487 QBS4B", "C4017 QB/MX", "C4017 QBBHP", "C4017 QBCHK", "4085 JIBLK", "4892 JIBLK", "C2726 QBP2S", "C2728 QBP2S", "C2947 QBN3Y",
            "C2951 QBN3Y", "89889 QBBLM", "1589 IMQLZ", "1671 IMSPZ", "1671 IMSQK", "2312 IMSQ1", "2561 IMGRN", "2561 IMP1O", "3036 IMAA8", "3036 IMDQC", "58032 IMF8Q", "58035 IMSDJ", "6786 QBSMB", "68168 IMRFF", "7287 IMQQ4", "91019 IMRFF", "91122 IMSW6", "91677 IME74", "97561 IMCHK", "C1323 IMGRN", "C1323 IMNOG", "C1325 IMLOV", "C1430 IMRFF", "C4010 QBSP9", "C4011 QBSP9", "C4014 QBDMH", "C4015 QBDMH", "C4016 QBDMH", "C4036 B4/DE",
            "C4057 IMDEN", "C4058 IMSQQ", "C4061 IMM6H", "C4064 IMDEI", "C4084 IMSQK", "C4084 IMSW6", "C4085 IMSQ6", "C4086 IMSQ4", "C4086 IMSQL", "C4087 IMSQ6", "C4090 IMCAH", "C4092 IMCAH", "C4093 IMMID", "C4093 IMNOG", "C4094 IMMID", "C4094 IMNOG", "C4095 IMMID", "C4147 QBCAH", "C4208 IMM6H", "C4221 IMMID", "C4222 IMSQ4", "C4222 IMSQL", "C4224 IMMID", "C4224 IMNOG", "C4250 IMAA8", "C4250 IMRFF", "C4252 IMCAH", "C4466 IMBLK",
            "C4556 IMCAH", "C4557 IMCAH", "C4561 IMQA7", "C4566 IMSQ4", "C4566 IMSQL", "C4653 IMSQQ"});


    /**
     * 空字符串
     */
    private static final String NULLSTR = "";

    /**
     * 最大日期
     */
    public static final LocalDateTime MAX_DATETIME = LocalDateTime.parse("2999-12-31T00:00:00");

    /**
     * 最小日期
     */
    public static final LocalDateTime MIN_DATETIME = LocalDateTime.parse("2000-01-01T00:00:00");

    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';

    /**
     * 星号
     */
    private static final String START = "*";

    /**
     * 获取参数不为空值
     *
     * @param value defaultValue 要判断的value
     * @return value 返回值
     */
    public static <T> T nvl(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * * 判断一个Collection是否为空， 包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * * 判断一个Collection是否非空，包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * * 判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组 * @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * * 判断一个对象数组是否非空
     *
     * @param objects 要判断的对象数组
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * * 判断一个对象是否是数组类型（Java基本型别的数组）
     *
     * @param object 对象
     * @return true：是数组 false：不是数组
     */
    public static boolean isArray(Object object) {
        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * 判断两个数值是否相等
     *
     * @param double1 数值1
     * @param double2 数值2
     * @return true：相等 false：不相等
     */
    public static boolean isEqual(Double double1, Double double2) {
        return Double.doubleToRawLongBits(double1) == Double.doubleToRawLongBits(double2);
    }

    /**
     * 判断两个数值是否相等
     *
     * @param double1 数值1
     * @param double2 数值2
     * @return true：不相等 false：相等
     */
    public static boolean isNotEqual(Double double1, Double double2) {
        return !isEqual(double1, double2);
    }

    /**
     * 去空格
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @return 结果
     */
    public static String substring(final String str, int start) {
        if (str == null) {
            return NULLSTR;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return NULLSTR;
        }

        return str.substring(start);
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @param end   结束
     * @return 结果
     */
    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return NULLSTR;
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return NULLSTR;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 下划线转驼峰命名
     */
    public static String toUnderScoreCase(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        // 前置字符是否大写
        boolean preCharIsUpperCase = true;
        // 当前字符是否大写
        boolean curreCharIsUpperCase = true;
        // 下一字符是否大写
        boolean nexteCharIsUpperCase = true;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0) {
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }

            curreCharIsUpperCase = Character.isUpperCase(c);

            if (i < (str.length() - 1)) {
                nexteCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }

            if (preCharIsUpperCase && curreCharIsUpperCase && !nexteCharIsUpperCase) {
                sb.append(SEPARATOR);
            } else if ((i != 0 && !preCharIsUpperCase) && curreCharIsUpperCase) {
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母大写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /**
     * 驼峰式命名法 例如：user_name->userName
     */
    public static String toCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder(s.length());
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == SEPARATOR) {
                upperCase = true;
            } else if (upperCase) {
                sb.append(Character.toUpperCase(c));
                upperCase = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 查找指定字符串是否匹配指定字符串列表中的任意一个字符串
     *
     * @param str  指定字符串
     * @param strs 需要检查的字符串数组
     * @return 是否匹配
     */
    public static boolean matches(String str, List<String> strs) {
        if (isEmpty(str) || isEmpty(strs)) {
            return false;
        }
        for (String testStr : strs) {
            if (matches(str, testStr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找指定字符串是否匹配指定字符串数组中的任意一个字符串
     *
     * @param str  指定字符串
     * @param strs 需要检查的字符串数组
     * @return 是否匹配
     */
    public static boolean matches(String str, String... strs) {
        if (isEmpty(str) || isEmpty(strs)) {
            return false;
        }
        for (String testStr : strs) {
            if (matches(str, testStr)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找指定字符串是否匹配
     *
     * @param str     指定字符串
     * @param pattern 需要检查的字符串
     * @return 是否匹配
     */
    public static boolean matches(String str, String pattern) {
        if (isEmpty(pattern) || isEmpty(str)) {
            return false;
        }

        pattern = pattern.replaceAll("\\s*", ""); // 替换空格
        int beginOffset = 0; // pattern截取开始位置
        int formerStarOffset = -1; // 前星号的偏移位置
        int latterStarOffset = -1; // 后星号的偏移位置

        String remainingURI = str;
        String prefixPattern = "";
        String suffixPattern = "";

        boolean result = false;
        do {
            formerStarOffset = indexOf(pattern, START, beginOffset);
            prefixPattern =
                    substring(
                            pattern, beginOffset, formerStarOffset > -1 ? formerStarOffset : pattern.length());

            // 匹配前缀Pattern
            result = remainingURI.contains(prefixPattern);
            // 已经没有星号，直接返回
            if (formerStarOffset == -1) {
                return result;
            }

            // 匹配失败，直接返回
            if (!result) return false;

            if (!isEmpty(prefixPattern)) {
                remainingURI = substringAfter(str, prefixPattern);
            }

            // 匹配后缀Pattern
            latterStarOffset = indexOf(pattern, START, formerStarOffset + 1);
            suffixPattern =
                    substring(
                            pattern,
                            formerStarOffset + 1,
                            latterStarOffset > -1 ? latterStarOffset : pattern.length());

            result = remainingURI.contains(suffixPattern);
            // 匹配失败，直接返回
            if (!result) return false;

            if (!isEmpty(suffixPattern)) {
                remainingURI = substringAfter(str, suffixPattern);
            }

            // 移动指针
            beginOffset = latterStarOffset + 1;

        } while (!isEmpty(suffixPattern) && !isEmpty(remainingURI));

        return true;
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object obj) {
        return (T) obj;
    }
}
