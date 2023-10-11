package com.demo.masterslavesample.utils;

import java.io.IOException;

public class Constants {
    public static final String GLOBAL_SECTION_NAME = "global";
    public static final String ENV = "env";
    public static final String RABBITMQ_SECTION_NAME = "rabbitmq";
    public static final String RABBITMQ_HOST = "host";
    public static final String RABBITMQ_PORT = "port";
    public static final String RABBITMQ_USERNAME = "username";
    public static final String RABBITMQ_PASSWORD = "password";
    public static final String RABBITMQ_VHOST = "virtualhost";

    //database
    public static final String REDIS_SECTION_NAME = "redis";
    public static final String REDIS_HOST = "host";
    public static final String REDIS_PORT = "port";
    public static final String REDIS_PASSWORD = "password";
    public static final String DB_SECTION_NAME = "database";
    public static final String DB_URL = "url";
    public static final String DB_USERNAME = "username";
    public static final String DB_PASSWORD = "password";
    public static final String MASTER_DB_URL = "master_url";
    public static final String MASTER_DB_USERNAME = "master_username";
    public static final String MASTER_DB_PASSWORD = "master_password";
    public static final String SLAVE_DB_URL = "slave_url";
    public static final String SLAVE_DB_USERNAME = "slave_username";
    public static final String SLAVE_DB_PASSWORD = "slave_password";
    public static final String DB_MAX_CONNECTION_POOL_SIZE = "max_connection_pool_size";
    public static final String DB_MIN_CONNECTION_POOL_SIZE = "min_connection_pool_size";
    public static final String DB_CONN_VALID_INTERVAL_MS = "connection_valid_interval_ms";
    public static final String DB_MAX_WAIT_CONN_MS = "max_wait_connection_ms";
    public static final String DB_SLOW_QUERY_DURATION_MS = "slow_query_duration_ms";

    //Queue
    public static final String QUEUE = "queue";
    public static final String CSV_QUEUE_SECTION_NAME = "csvQueue";
    public static final String TRIP_QUEUE_SECTION_NAME = "tripQueue";
    public static final String UPDATE_QUEUE_SECTION_NAME = "updateQueue";
    public static final String ALTERNATE_TRIP_QUEUE_SECTION_NAME = "alternateTripQueue";
    public static final String CANCEL_BOOKING_QUEUE_SECTION_NAME = "cancelBookingQueue";
    public static final String EXCHANGE = "exchange";
    public static final String ROUTING_KEY = "routing_key";
    public static final String STAGE_TRIPS_PREFIX = "tms_stage_trips:";
    public static final String STAGE_ALERTS_PREFIX = "tms_stage_alerts:";
    public static final String STATIC_DATA_PREFIX = "tms_static_data:";

    //URLs
    public static final String URLS_SECTION_NAME = "urls";
    public static final String MTICKETING_URL = "mticketing_url";
    public static final String AFCS_BASE_URL = "afcs_base_url";
    public static final String SCHEDULER_BASE_URL = "scheduler_base_url";
    public static final String GPS_BASE_URL = "gps_base_url";
    public static final String LIVE_TRACKING_URL = "live_tracking_url";
    public static final String SLACK_WEBHOOK_URL = "slack_webhook_url";
    public static final String DASHBOARD_ALERT_SLACK_WEBHOOK_URL = "dashboard_alert_slack_webhook_url";
    public static final String STALE_PLANNED_TRIPS_SLACK_WEBHOOK_URL = "stale_planned_trips_slack_webhook_url";

    public static final String CONSTANTS_SECTION_NAME = "constants";
    public static final String AGENCY = "agency";
    public static final String GPS_PREMIUM_BUS_AGENCY = "chalo%20bus";
    public static final String GPS_MODE = "bus";
    public static final double RADIAN = 57.2958;

    //Params
    public static final String PARAMS_SECTION_NAME = "params";
    public static final String BUS_NOT_REACHED_DISTANCE_METERS = "bus_not_reached_distance_meters";
    public static final String NON_ORIGIN_START_DISTANCE_METERS = "non_origin_start_distance_meters";
    public static final String CANCEL_BOOKINGS_LIMIT_MILLIS = "cancel_bookings_limit_millis";
    public static final String FIND_ALTERNATE_TRIPS_LIMIT_MILLIS = "find_alternate_trips_limit_millis";
    public static final String ROUTE_EDIT_TRIP_START_LIMIT_MILLIS = "route_edit_trip_start_limit_millis";
    public static final String TRIP_CREATION_BATCH_SIZE = "trip_creation_batch_size";
    public static final String CANCEL_BOOKING_VALIDATE_WINDOW_MILLIS = "cancel_booking_validate_limit_millis";
    public static final String RESCHEDULE_BOOKING_VALIDATE_WINDOW_MILLIS = "reschedule_booking_validate_limit_millis";
    public static final String PRE_BOOKING_LIMIT_MINS = "pre_booking_limit_mins";
    public static final String TRIP_CANCELLATION_LIMIT = "trip_cancellation_limit";
    public static final String ONBOARDING_TIME_LIMIT_MINS =
            "onboarding_time_limit_mins";
    public static final String TRIP_SLOT_FILTER_INITIAL_LIMIT_MINS =
            "trip_slot_filter_initial_limit_mins";
    public static final String GPS_TIME_LIMIT_MINS = "gps_time_limit_mins";
    public static final String LIVE_SLOT_ETA_LIMIT_MINS =
            "live_slot_eta_limit_mins";

    //Notification constants
    public static final String NOTIFICATION_METADATA_SERVICE = "TMS";
    public static final String TRIP_START_REASON = "Trip start.";
    public static final String NOTIFICATION_PRODUCT_TYPE = "mobileTicket";
    public static final String NOTIFICATION_PRODUCT_SUB_TYPE = "premiumReserveTicket";
    public static final String TRIP_START_NOTIFICATION_TITLE = "\uD83D\uDD14 Trip started";
    public static final String TRIP_START_NOTIFICATION_TYPE = "PB_TRIP_STARTED";
    public static final String TRIP_START_CLICK_ACTION = ".ViewPremiumReserveTicketActivation";


    public static String getStageTripsKey(String city, String uploadId) {
        return STAGE_TRIPS_PREFIX + city + ":" + uploadId;
    }

    public static String getStageAlertsKey(String city, String alertType, String tripId) {
        return STAGE_ALERTS_PREFIX + city + ":" + alertType + ":" + tripId;
    }

    public static String getStaticDataKey(String city) {
        return STATIC_DATA_PREFIX + city;
    }

    public static long fetchRouteEditTripStartLimit() throws IOException {
        String value = IniUtils.getInstance().getValue(Constants.PARAMS_SECTION_NAME, Constants.ROUTE_EDIT_TRIP_START_LIMIT_MILLIS);
        return Long.parseLong(value);
    }

    public static int fetchTripCreationBatchSize() throws IOException {
        String value = IniUtils.getInstance().getValue(Constants.PARAMS_SECTION_NAME, Constants.TRIP_CREATION_BATCH_SIZE);
        return Integer.parseInt(value);
    }

    public static int fetchBusNotReachedLimit() throws IOException {
        return Integer.parseInt(IniUtils.getInstance().getValue(
                Constants.PARAMS_SECTION_NAME, Constants.BUS_NOT_REACHED_DISTANCE_METERS));
    }

    public static int fetchNonOriginStartLimit() throws IOException {
        return Integer.parseInt(IniUtils.getInstance().getValue(
                Constants.PARAMS_SECTION_NAME, Constants.NON_ORIGIN_START_DISTANCE_METERS));
    }

    public static int fetchPreBookingLimit() throws IOException {
        return Integer.parseInt(IniUtils.getInstance().getValue(
                Constants.PARAMS_SECTION_NAME, Constants.PRE_BOOKING_LIMIT_MINS));
    }

    public static int fetchTripCancellationLimit() throws IOException {
        return Integer.parseInt(IniUtils.getInstance().getValue(
                Constants.PARAMS_SECTION_NAME, Constants.TRIP_CANCELLATION_LIMIT));
    }

    public static int fetchTripSlotFilterInitialLimit() throws IOException {
        return Integer.parseInt(IniUtils.getInstance().getValue(
                Constants.PARAMS_SECTION_NAME, Constants.TRIP_SLOT_FILTER_INITIAL_LIMIT_MINS));
    }

    public static int fetchGPSTimeStampLimit() throws IOException {
        return Integer.parseInt(IniUtils.getInstance().getValue(
                Constants.PARAMS_SECTION_NAME, Constants.GPS_TIME_LIMIT_MINS));
    }

    public static int fetchLiveSlotETALimit() throws IOException {
        return Integer.parseInt(IniUtils.getInstance().getValue(
                Constants.PARAMS_SECTION_NAME, Constants.LIVE_SLOT_ETA_LIMIT_MINS));
    }

    public static int fetchOnboardingTimeLimit() throws IOException {
        return Integer.parseInt(IniUtils.getInstance().getValue(
                Constants.PARAMS_SECTION_NAME,
                Constants.ONBOARDING_TIME_LIMIT_MINS));
    }

}
