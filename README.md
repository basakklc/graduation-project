# graduation-project

#### NOT
graduation-project repository içerisindeki ***MVVMDemoProject*** dizini -> Android tarafı kodları, ***ogrenciyasami-api*** dizini -> Laravel API tarafı kodları temsil eder.


https://user-images.githubusercontent.com/34400940/139649481-6300dd02-7cd4-4a93-a291-fd73d8b98e98.mp4/

## Proje Tanımı
Projemiz iki ana başlık çerçevesinde geliştirilmişir. 
+ Birinci bölümde Çanakkale Onsekiz Mart Üniversitesi’nde düzenlenen topluluk etkinlikleri üzerine, 
+ İkinci bölümde ise öğrencilerin hızlı ulaşabilmesi için ev,eşya ilanlarının bir araya getirilmesi üzerine yoğunlaşılmıştır. 

##### ***Topluluk Etkinlikleri Kısmı:***
Öğrenciler, düzenlenen tüm etkinlikleri listeleyip etkinlik hakkındaki bilgilere kolaylıkla ulaşabilecek, gerektiğinde bilgilerde güncelleme yapabilecektir. Aynı zamanda kendi kriterleri doğrultusunda etkinlikleri filtreleyebilecekler. 
Topluluk etkinliklerine ek olarak öğrencilerin ders ilanı oluşturabileceği bir yapı sunulması da hedeflenmiştir. 

##### ***Öğrenci Yaşamı Kısmı:***
Öğrenciler Çanakkale’deki kiralık evleri ve diğer öğrenciler tarafından satılmak istenen eşyaları görüntüleyebilecekleri gerekli durumlarda ilan güncelleme, kaldırma yapabilecekleri bir yapı sunulması hedeflenmiştir. Aynı zamanda uygulamayı kullanacak öğrenciler ev arkadaşı da arayabilirler.
Öğrenciler yine istekleri doğrultusunda gerekli filtreleme işlemleri yapabilirler.

## Proje Hedeleri
1. Öğrencilerin düzenlenen topluluk etkinliklerinden haberdar olmasını sağlamak.
2. Etkinliklerdeki etkinliğe katılıp katılmama durumuna göre kontenjan kontrolünü sağlamak.
3. Öğrencilerin katılacağı etkinlikleri takvim üzerinden takibini ve bildirim almasını sağlamak.
4. Öğrencilerin kolay ve hızlı şekilde ev ve eşya bulmasına imkan sunmak.
5. Öğrencilerin kendi aralarında iletişim kurmalarına fırsat tanımak.
6. Kurulan iletişimler sonucunda en ekonomik şartlarda eşya satın almak ve ev kiralayabilmek.
7. Birebir kurulan iletişimler sayesinde istenilen kriterlere uygun ev arkadaşı bulmak.

## UI Tasarımları
Uygulama geliştirmeye başlamadan önce kullanacağımız bileşenleri kolaylıkla belirleyebilmek ve uygulama gereksinimlerini anlayabilmek için taslak niteliğinde UI tasarımlarını oluşturduk.
___Adobe XD___ üzerinden arayüzleri hazırladıktan sonra Prototype yardımıyla senaryo işleyişini takip ettik.

<img src="https://user-images.githubusercontent.com/34400940/139630797-75cf18a5-15cd-49c1-96f0-022d60256d94.png" width="400" /> <img src="https://user-images.githubusercontent.com/34400940/139630758-d50a47fc-5203-4a52-adf6-b231310d2337.png" width="500"/><img src="https://user-images.githubusercontent.com/34400940/139630840-552e1a3a-7f46-4dd0-aaf5-ee88301a7bb4.png" width="450" />

## Kullanılan Teknolojiler
##### _Mobil Uygulama Platformu_
Hedef kullanıcı kitlesini ve uygulama kapsamını göz önünde bulundurduğumuzda mobil cihazlar üzerinden daha kolay ve verimli şekilde gereksinimlerimizin karşılanabileceğini düşündük. Bu yüzden uygulama ortamının mobil tarafta olmasının daha kullanışlı olacağına karar verdik.
Geliştirme için __Android Studio__ ortamını belirledik. Daha az kod satırıyla daha güvenli kod yazmaya olanak sağladığı için ve diğer avantajları da dikkate alındığında __Kotlin__ programlama diliyle de uygulamamızı kodlamaya karar verdik.

##### _Veritabanı Tasarımı_
Veritabanı tasarımımızı öncelikle __MySQL Workbench__ üzerinde yaparak tablolar arasındaki ilişkileri daha net kavradık. Tüm modüllerin ER şemalarını hazırladıktan sonra __PhpStorm__ üzerinden migration işlemleriyle veritabanımızı geliştirme ortamımıza aktarmış olduk.

<img src="https://user-images.githubusercontent.com/34400940/139633240-d36c4dc5-c76b-4a02-82c0-d34ad3504c09.png" width="500" /> <img src="https://user-images.githubusercontent.com/34400940/139633433-1cee6827-6f4f-4b70-aef4-8809692ab6d0.png" width="250" />

##### _REST API_
Aşağıdaki gereksinimler doğrultusunda API hazırlamaya karar verdik.
+ Uygulamamızda verilerin uzak sunucuda tutulması, internet üzerinden ulaşılması
+ Ayrıca veritabanı işlemlerinin tek merkezde yapılıp platform ve dil bağımsızlığının sağlanması

Hem hızlı uygulama geliştirmeyi mümkün kılan bir kod altyapısını kullanıcılarına sunarak, daha sade bir ortamda kullanıcılara kolaylık sağladığı için hem de MVC yapısında API uygulama geliştirmeye imkan sunduğu için __Laravel__ framework’üne karar verdik. IDE olarak __PHP Storm__ kullanmayı tercih ettik. Veritabanıyla etkili şekilde çalışmak için Laravelin sunduğu __Elequent ORM__ yapısını kullandık.
__Postman__ ile isteklerimizi test ettik. Kodlarımızı daha temiz ve değişime açık şekilde yazmak için projemizi bir mimari üzerine inşa ettik. Ayrıca çeşitli tasarım desenlerinden yararlandık,__Repository Pattern__ bunlardan biriydi.

<img src="https://user-images.githubusercontent.com/34400940/139634046-b106b54e-93c5-434d-984f-bf784a566158.png" width="250" />


## Mobil Uygulama Geliştirme - Android Architecture Components
##### _Retrofit_
Veri çekme ve network işlemleri için API’lara kolaylıkla erişebilme, test edilebilir ve kolay kullanım özelliklerinden dolayı açık kaynak kodlu REST istemcisi olan __Retrofit'e__ karar verdik.

##### _MVVM_
Kullanıcı arabirimiyle ilişkili verileri yaşam döngüsü bilinçli şekilde tutmayı ve yönetmeyi sağladığı bu sayede de yapılandırma değişikliklerini koruduğu için __MVVM__ mimarisini kullanmaya karar verdik.
Use Case'ler ile veri akışı mantığını uygulayarak bu akışın view modellerde yeniden kullanılabilir olmasını sağladık.

<img src="https://user-images.githubusercontent.com/34400940/139634375-50f73f49-f1a7-4dec-b8e2-0d257c6cf2ae.png" width="250" />

##### _RxJava_
Arayüz işlemleri ile istek sonuçlarının birbirini bloklamaması ,ana ve arka plan iş parçacığını birbirinden ayrılabilmesi için eş zamansız çağrılar yapmamız gerekliydi. Bundan dolayı isteklerimizi işleyebilmek için aşağıdaki avantajları da göz önünde bulundurarak __RxJava__ kullanmaya karar verdik.
+ Operatörler ve veri akış manipülasyonlarının kolaylıkla yapılabilmesi
+ Observable veri tipleri sayesinde istek yapısına uygun veriler işlenebilmesi
+ Zincir yapısı sayesinde iç içe ağ aramasından kurtarması
+ Kodun çalıştırılacağı zamanlayıcıyı değiştirme olanağı sağlaması
+ Hata işlemeyi kolaylaştırması

##### _Data Binding_
Arayüzümüz kullanıcı odaklı olduğu için daha hızlı ve düzenli kod yazmamızı sağlayacak __Data Binding__ kütüphanesini kullanarak veri modelini XML dosyasına bağladık.

##### _Navigation_
Uygulamamızın baştan sona bir bütün halinde gezinme akışını sağlamak için __Jetpack__ mimari bileşenlerde bulunan __Navigation__ componenti kullanmaya karar verdik. Bu sayede fragmentler arası geçişi kolaylıkla ele alıp geri akışları etkili şekilde tamamladık.

<img src="https://user-images.githubusercontent.com/34400940/139635072-1db1341c-0deb-4833-8ee8-72ae0268ec6b.png" width="300" />



