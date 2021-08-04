package com.example.demo.common.constant

/**
 * This class is used for declaring constant values which ar used in app
 */
class AppConstant {

    /**
     * This annotation is used for some default values which will be used in app
     */
    annotation class DefaultValues {
        companion object {
            const val DatabaseName: String = "DemoProject"
            const val DeviceType: String = "1" //1-Android, 0-iOS
            const val UserRole: String =
                    "5" //2-Customer, 3-Business, 4-Trader, 5-Driver (May Change when business comes)
            const val IsBetaVersion: Boolean = true
            const val LoginModuleImageRatio: Int = 27
            const val ServiceCategoryId: Int = 0 //For Driver Service Category Id is 0
            const val dateFormat = "yyyy-MM-dd HH:mm:ss"
            const val orderOnFormat = "dd MMM yyyy 'at' hh:mm a"
            const val orderDateTime = "dd/MM/yyyy 'at' hh:mm a"
            const val orderRate = "dd/MM/yyyy"
            const val vehicleTypeCar = "Car"
            const val vehicleTypeBike = "Bike"
            const val vehicleTypeCycle = "Cycle"
            const val NOTI_LOGS = "notification_logs"
            const val defaultLatitude = "51.509865"
            const val defaultLongitude = "-0.118092"
            const val remoteMessage = "remote_message"
            const val List = "List"

            //Order
            const val currentOrderTab = 0
            const val pastOrderTab = 1


            //Profile
            const val personalInformationVal = 0
            const val identityProofVal = 1


            //Add Identity
            const val ALPHA_ENABLE = 0.5f
            const val ALPHA_DISABLE = 1f


        }
    }


    /**
     * This annotation is used for opening screen after app launch
     */
    annotation class OpenScreenType {
        companion object {
            const val OpenScreenValue = "OpenScreenValue"
            const val LoginScreen: Int = 1
            const val MainHomeScreen: Int = 2
        }
    }

    /**
     * This annotation is used for opening Order Details Screen Type
     */
    annotation class OpenDetailsOrderScreenType {
        companion object {
            const val FirstOrder: Int = 0
            const val SecondOrder: Int = 1

        }
    }

    /**
     * This annotation is used for opening Order Details Screen
     */
    annotation class OpenDetailsScreenType {
        companion object {
            const val OpenScreenValue = "OpenScreenValue"
            const val RequestDetailsScreen: Int = 1
            const val CurrentOrderDetailsScreen: Int = 2
            const val PastOrderDetailsScreen: Int = 3
            const val ChatScreen: Int = 4
        }
    }

    /**
     * Used for Earning type
     */
    annotation class Earning {
        companion object {
            const val Daily = 0
            const val Weekly = 1
            const val Monthly = 2

        }
    }

    /**
     * This annotation is used for Notification Type on Notification Screen
     */
    annotation class NOTIFICATION_TYPES {
        companion object {
            const val NOTIFICATION_DRIVER_VERIFICATION = 1
            const val NOTIFICATION_SHOP_ORDER_REQUEST = 2
            const val NOTIFICATION_DRIVER_ORDER_REQUEST = 3
            const val NOTIFICATION_RATING = 4
            const val NOTIFICATION_ORDER_STATUS_CHANGE = 5
            const val NOTIFICATION_ORDER_RECEIPT_UPLOADED = 6
            const val NOTIFICATION_ORDER_CHAT = 7
            const val NOTIFICATION_EARN_POINT = 8
        }
    }

    /**
     * Used for Url type
     */
    annotation class UrlType {
        companion object {
            val ShopLogo = "Shop"
            val ShopGallery = "ShopGallery"
            val ProductLogo = "Product"
            val ProductGallery = "ProductGallery"
            val Users = "Users"
            val Driver = "Driver"
            val Orders = "Orders"
        }
    }

    /**
     * Used for Url type
     */
    annotation class UrlReplaceValue {
        companion object {
            val ImageName = "{ImageName}"
            val Id = "{Id}"
        }
    }


    /**
     * This annotation class is used for view type in adapter class.
     */
    annotation class ViewType {
        companion object {
            const val Title: Int = 0
            const val Data: Int = 1
            const val NoData: Int = 2
        }
    }

    /**
     * This annotation class is used for view type in adapter class.
     */
    annotation class ChatViewType {
        companion object {
            const val SenderData: Int = 1
            const val ReceiverData: Int = 2
            const val NoData: Int = 3
        }
    }

    /**
     * This annotation class is used for Login type in adapter class.
     */
    annotation class LoginType {
        companion object {
            const val Normal = "1"
            const val FaceBook = "2"
            const val Google = "3"
            const val Apple = "4"
        }
    }

    /**
     * This annotation class is used for request code type in app for activity is result
     */
    annotation class RequestCodeType {
        companion object {
            const val Google = 1001
            const val Facebook = 1002
        }
    }

    /**
     * Date filter type
     */
    annotation class UserAvailableCheck {
        companion object {
            const val Is_User_Available_Register = 0
            const val Is_User_Available_Login = 1
        }
    }

    /**
     * This annotation class is used for social media user exist check : is_for_validation
     */
    annotation class SocialMediaUserCheck {
        companion object {
            const val LoginUser = "1"
            const val RegisterUser = "0"
        }
    }

    /**
     * Date filter type
     */
    annotation class DateFilterType {
        companion object {
            const val Default = "Default"
            const val Day = "Day"
            const val Week = "Week"
            const val Month = "Month"
            const val Custom = "Custom"
        }
    }

    /**
     * Used Order Status
     */
    annotation class OrderStatus {
        companion object {
            const val Pending = "Pending"
            val AcceptedByShop = "AcceptedByShop"
            val AcceptedByDriver = "AcceptedByDriver"
            val Confirmed = "Confirmed"
            val OrderLeft = "OrderLeft"
            val Delivered = "Delivered"
            val RejectedByShop = "RejectedByShop"
            val Rejected = "Rejected"
            val Cancelled = "Cancelled"
        }
    }


}